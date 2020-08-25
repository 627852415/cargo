package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.dao.PaperDao;
import com.lxtx.im.admin.dao.dao.PaperTypeDao;
import com.lxtx.im.admin.dao.model.Paper;
import com.lxtx.im.admin.dao.model.PaperType;
import com.lxtx.im.admin.service.CargoService;
import com.lxtx.im.admin.service.cargo.req.BasePageReq;
import com.lxtx.im.admin.service.cargo.req.PaperListPage;
import com.lxtx.im.admin.service.response.BasePageResp;
import com.lxtx.im.admin.service.vo.PaperTypeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private PaperTypeDao paperTypeDao;
    @Autowired
    private PaperDao paperDao;

    /**
     * 服务范围
     * @param pid
     * @return
     */
    @Override
    public BaseResult serviceRange(String pid){
        EntityWrapper<Paper> paperTypeEntityWrapper = new EntityWrapper<>();
        paperTypeEntityWrapper.setSqlSelect("id,ref_id,name,id,author,create_time");
        paperTypeEntityWrapper.eq("ref_id",pid);
        paperTypeEntityWrapper.orderBy("update_time",false);
        BasePageReq basePageReq = new BasePageReq();
        Page<Paper> page = paperDao.selectPage(basePageReq.getPage(),paperTypeEntityWrapper);
        return BaseResult.success(page);
    }

    


    /**
     * 底部最新消息
     * @return
     */
    @Override
    public List<Paper> newPaper(){
        EntityWrapper<Paper> paperTypeEntityWrapper = new EntityWrapper<>();
        paperTypeEntityWrapper.setSqlSelect("id,ref_id,name,id,author,create_time");
        paperTypeEntityWrapper.orderBy("create_time",false);
        BasePageReq basePageReq = new BasePageReq();
        basePageReq.setSize(7);
        Page<Paper> page = paperDao.selectPage(basePageReq.getPage(),paperTypeEntityWrapper);
        List<Paper> listPage =  page.getRecords();
        if(CollectionUtils.isNotEmpty(listPage)){
            for(Paper paper:listPage){
                paper.setId("/index/paper?id="+paper.getId());
            }
        }
        return listPage;
    }


    /**详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult detail(String id){
        Paper paper = new Paper();
        paper.setId(id);
        return BaseResult.success(paperDao.selectOne(paper));
    }

    @Override
    public BaseResult listPage(PaperListPage basePageReq){
        EntityWrapper<Paper> paperTypeEntityWrapper = new EntityWrapper<>();
        paperTypeEntityWrapper.eq("ref_id",basePageReq.getTypeId());
        paperTypeEntityWrapper.setSqlSelect(" ref_id,name,id,author,create_time");
        Page page = paperDao.selectPage(basePageReq.getPage(),paperTypeEntityWrapper);
        BasePageResp<Paper> basePageResp = new BasePageResp();
        BeanUtils.copyProperties(page,basePageResp);
        return BaseResult.success(basePageResp);
    }

    @Override
    @Transactional
    public boolean savePapaer(String refId, String name, String content){
        Paper paper = new Paper();
        paper.setAuthor("sys");
        paper.setName(name);
        paper.setRefId(refId);
        paper.setContent(content);
        paperDao.insert(paper);


        //找出最大序号
        int maxSortNo = 0;
        EntityWrapper<PaperType> paperTypeEntityWrapper = new EntityWrapper<>();
        paperTypeEntityWrapper.eq("p_id",refId);
        paperTypeEntityWrapper.setSqlSelect("max(sort_no)");
        PaperType maxPaperType = paperTypeDao.selectOne(paperTypeEntityWrapper);
        if(maxPaperType!=null){
            maxSortNo = maxPaperType.getSortNo()+1;
        }
        PaperType paperType = new PaperType();
        paperType.setPId(refId);
        paperType.setPaperId(paper.getId());
        paperType.setName(name);
        paperType.setSortNo(maxSortNo);
        paperTypeDao.insert(paperType);
        return true;
    }

    @Override
    @Transactional
    public boolean delPapaer(String id){
        Paper paper = new Paper();
        paper.setId(id);
        paperDao.delete(paper);

        PaperType paperType = new PaperType();
        paperType.setPaperId(id);
        paperTypeDao.delete(paperType);

        return true;
    }

    @Override
    @Transactional
    public boolean update(String id, String name, String content){
        Paper paper = new Paper();
        paper.setId(id);
        paper.setName(name);
        paper.setContent(content);
        paperDao.updateById(paper);

        //更新类型
        PaperType paperType = new PaperType();
        paperType.setPaperId(id);
        paperType = paperTypeDao.selectOne(paperType);
        paperType.setName(name);
        paperTypeDao.updateById(paperType);

        return true;
    }


    /**
     * 导航栏菜单
     * @return
     */
    @Override
    public BaseResult paperList(){

        List<PaperTypeVo> voList = Lists.newArrayList();
        EntityWrapper<PaperType> entityWrapper = new EntityWrapper<>();
        entityWrapper.orderBy("sort_no",false);
        List<PaperType> paperTypes = paperTypeDao.selectList(entityWrapper);
        if(CollectionUtils.isNotEmpty(paperTypes)){
            List<PaperType> parentMenuListSrc =  paperTypes.stream().
                    filter(d ->"0".equals(d.getPId())).collect(Collectors.toList());
            //处理菜单格式
            fetchMenuList(paperTypes, parentMenuListSrc, voList);
        }
        voList = voList.stream().sorted(Comparator.comparingInt(PaperTypeVo::getSortNo)).collect(Collectors.toList());
        return BaseResult.success(voList);
    }


    private void fetchMenuList(List<PaperType> menuList, List<PaperType> parentMenuListSrc, List<PaperTypeVo> menuRespList) {
        for(PaperType  parentMenu:parentMenuListSrc){
            PaperTypeVo sysLoginMenuResp = new PaperTypeVo();
            BeanUtils.copyProperties(parentMenu,sysLoginMenuResp);
            //获取下级菜单
            List<PaperType> subMenuList =  menuList.stream().
                    filter(d ->d.getPId().equals(parentMenu.getId())).collect(Collectors.toList());
            //通过递归再获取有无下级菜单
            if(CollectionUtils.isNotEmpty(subMenuList)){
                List<PaperTypeVo>  subMenuRespList = JSON.parseArray(JSON.toJSONString(subMenuList),PaperTypeVo.class);
                for(PaperTypeVo sysLoginMenuResp1:subMenuRespList){
                    sysLoginMenuResp1.setSub(
                            addSubMenuLists(menuList, sysLoginMenuResp1.getId(), new ArrayList<>(), sysLoginMenuResp1));
                }
                sysLoginMenuResp.setSub(subMenuRespList);
            }
            menuRespList.add(sysLoginMenuResp);
        }
    }

    private List<PaperTypeVo> addSubMenuLists(List<PaperType> srcList, String pId,List<PaperTypeVo>  subMenuRespList, PaperTypeVo sysLoginMenuResp) {
        List<PaperType> subsubMenuList =  srcList.stream().
                filter(d ->d.getPId().equals(pId)).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(subsubMenuList)){
            return subMenuRespList;
        }
        List<PaperTypeVo> data = JSON.parseArray(JSON.toJSONString(subsubMenuList),PaperTypeVo.class);
        for(PaperTypeVo sysLoginMenuResp1:data){
            sysLoginMenuResp.setSub(data);
            subMenuRespList.add(sysLoginMenuResp1);
            return addSubMenuLists(srcList,sysLoginMenuResp1.getId(),subMenuRespList,sysLoginMenuResp1);
        }
        return null;
    }





}

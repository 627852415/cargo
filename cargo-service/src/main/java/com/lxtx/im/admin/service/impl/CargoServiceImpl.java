package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.dao.dao.PaperDao;
import com.lxtx.im.admin.dao.dao.PaperTypeDao;
import com.lxtx.im.admin.dao.model.Paper;
import com.lxtx.im.admin.dao.model.PaperType;
import com.lxtx.im.admin.service.CargoService;
import com.lxtx.im.admin.service.cargo.req.BasePageReq;
import com.lxtx.im.admin.service.cargo.req.PaperListPage;
import com.lxtx.im.admin.service.cargo.req.SaveReq;
import com.lxtx.im.admin.service.dto.Mpages;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.response.BasePageResp;
import com.lxtx.im.admin.service.vo.PaperTypeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private PaperTypeDao paperTypeDao;
    @Autowired
    private PaperDao paperDao;


    @Override
    public BaseResult deleteMenu(String id){
        PaperType paperType = new PaperType();
        paperType.setPId(id);
        if(CollectionUtils.isNotEmpty(paperTypeDao.selectList(paperType))){
            throw LxtxBizException.newException("存在子区域，不允许删除!");
        }

        Paper paper = new Paper();
        paper.setOneRef(id);
        if(CollectionUtils.isNotEmpty(paperDao.selectList(paper))){
            throw LxtxBizException.newException("该区域发布过文章，不允许删除!");
        }

        Paper paper2 = new Paper();
        paper2.setTwoRef(id);
        if(CollectionUtils.isNotEmpty(paperDao.selectList(paper2))){
            throw LxtxBizException.newException("该区域发布过文章，不允许删除!");
        }

        paperTypeDao.deleteById(id);
        return BaseResult.success();
    }

    @Override
    public BaseResult menuListPages(PaperListPage paperListPage){
        EntityWrapper<PaperType> paperTypeEntityWrapper = new EntityWrapper<>();
        paperTypeEntityWrapper.isNotNull("code");
        Page<PaperType> page = paperTypeDao.selectPage(paperListPage.getPage(),paperTypeEntityWrapper);
        return BaseResult.success(page);
    }


    @Override
    @Transactional
    public boolean updateServicePaper(SaveReq req){
        Paper paper = new Paper();
        paper.setId(req.getId());
        paper.setName(req.getName());
        paper.setContent(req.getContent());
        paper.setOneRef(req.getOneRefId());
        if(StringUtils.isEmpty(req.getTwoRefId())){
            paper.setTwoRef(" ");
        }else{
            paper.setTwoRef(req.getTwoRefId());
        }
        paperDao.updateById(paper);

        return true;
    }

    @Override
    @Transactional
    public boolean updateMenu(SaveReq req){
        PaperType paper = new PaperType();
        paper.setId(req.getId());
        paper.setName(req.getName());
        paperTypeDao.updateById(paper);
        return true;
    }

    @Override
    public boolean saveServiceOneMeun(String pid, String name,Integer topLevel){
        PaperType paperType = new PaperType();
        paperType.setName(name);
        paperType.setPId(pid);
        paperType.setTopLevel(topLevel);
        paperType.setCode(pid+"+"+name);
        paperTypeDao.insert(paperType);

        return true;
    }

    @Override
    public boolean saveServiceTwoMeun(String pid, String name, Integer topLevel){

        PaperType paperType = new PaperType();

        String codeID = pid;

        EntityWrapper<PaperType> paperTypeEntityWrapper = new EntityWrapper<>();
        paperTypeEntityWrapper.eq("p_id",pid);
        paperTypeEntityWrapper.orderDesc(Arrays.asList("id"));
        List<PaperType> paperTypes = paperTypeDao.selectList(paperTypeEntityWrapper);
        if(CollectionUtils.isNotEmpty(paperTypes)){
            pid = paperTypes.get(0).getId();
        }


        paperType.setName(name);
        paperType.setPId(pid);
        paperType.setTopLevel(topLevel);
        paperType.setCode(codeID+"+"+name);
        paperTypeDao.insert(paperType);

        return true;
    }

    @Override
    public List<PaperType> selectOneMeunList(String pid){
        EntityWrapper<PaperType> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("p_id",pid);
        entityWrapper.eq("top_level",2);
        List<PaperType> paperTypes = paperTypeDao.selectList(entityWrapper);
        return paperTypes;
    }

    @Override
    public List<PaperType> selectTwoLikeMeunList(String pid){
        EntityWrapper<PaperType> entityWrapper = new EntityWrapper<>();
        entityWrapper.like("code",pid);
        List<PaperType> paperTypes = paperTypeDao.selectList(entityWrapper);
        return paperTypes;
    }

    @Override
    public List<PaperType> selectTwoMeunList(String pid){
        EntityWrapper<PaperType> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("p_id",pid);
        List<PaperType> paperTypes = paperTypeDao.selectList(entityWrapper);
        paperTypes =  paperTypes.stream().filter(o->!(o.getTopLevel()!=null&&o.getTopLevel()==1)).collect(Collectors.toList());
        return paperTypes;
    }

    @Override
    public List<PaperType> selectSaveOneMeunList(String pid){
        EntityWrapper<PaperType> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("p_id",pid);
        List<PaperType> paperTypes = paperTypeDao.selectList(entityWrapper);
        return paperTypes;
    }


    @Override
    public BaseResult saveServiceTwoMeun(String pid) {
        PaperType paperType = new PaperType();
        paperType.setPId(pid);
        return BaseResult.success(paperTypeDao.selectList(paperType));
    }

    @Override
    @Transactional
    public boolean saveServicePapaer(SaveReq req){
        Paper paper = new Paper();
        BeanUtils.copyProperties(req,paper);
        paper.setOneRef(req.getOneRefId());
        paper.setTwoRef(req.getTwoRefId());
        paper.setAuthor("sys");
        paperDao.insert(paper);
        return true;
    }


    /**
     * 服务范围
     * @return
     */
    @Override
    public BaseResult serviceRange(String oneRef,String twoRef,String current){
        EntityWrapper<Paper> paperTypeEntityWrapper = new EntityWrapper<>();
        paperTypeEntityWrapper.setSqlSelect("id,ref_id,name,id,author,create_time");
        if(StringUtils.isNotEmpty(oneRef)){
            paperTypeEntityWrapper.eq("one_ref",oneRef);
        }else if(StringUtils.isNotEmpty(twoRef)){
            paperTypeEntityWrapper.eq("two_ref",twoRef);
        }
        paperTypeEntityWrapper.orderBy("update_time",false);
        BasePageReq basePageReq = new BasePageReq();
        basePageReq.setSize(3);
        if(StringUtils.isNotEmpty(current)){
            basePageReq.setCurrent(Integer.parseInt(current));
        }
        Page<Paper> page = paperDao.selectPage(basePageReq.getPage(),paperTypeEntityWrapper);
        Mpages mpages = new Mpages();
        List<Paper> list = page.getRecords();
        page.setRecords(list);
        if(CollectionUtils.isNotEmpty(page.getRecords())){
            List<String> pagesNum = Lists.newArrayList();
            for(int i = 1 ;i<=page.getPages();i++){
                pagesNum.add(i+"");
            }
            mpages.setPagesNums(pagesNum);
            for(Paper paper:page.getRecords()){
                paper.setTimeStr(
                        DateUtils.getDateFormat(paper.getCreateTime(),DateUtils.DATE_FORMAT_YYYY_MM_DD));
            }
        }
        mpages.setPage(page);
        return BaseResult.success(mpages);
    }


    @Override
    public  List<PaperType> serviceCountryRange(){
        EntityWrapper<PaperType> paperTypeEntityWrapper = new EntityWrapper<>();
        paperTypeEntityWrapper.isNotNull("code");
        paperTypeEntityWrapper.ne("p_id",6);
        BasePageReq basePageReq = new BasePageReq();
        basePageReq.setSize(5);
        Page<PaperType> page = paperTypeDao.selectPage(basePageReq.getPage(),paperTypeEntityWrapper);
        return page.getRecords();
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
    public BaseResult detailMenu(String id){
        PaperType paperType = new PaperType();
        paperType.setId(id);
        return BaseResult.success(paperTypeDao.selectOne(paperType));
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
                    if(sysLoginMenuResp1.getName().equals("东南亚")){
                        System.out.println("123");
                    }
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

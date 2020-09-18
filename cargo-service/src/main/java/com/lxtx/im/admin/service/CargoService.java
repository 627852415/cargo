package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.Paper;
import com.lxtx.im.admin.dao.model.PaperType;
import com.lxtx.im.admin.service.cargo.req.PaperListPage;
import com.lxtx.im.admin.service.cargo.req.SaveReq;

import java.util.List;

/**
 * @author Lin hj
 * @title: CargoService
 * @projectName git_work
 * @description: TODO
 * @date 2020/4/1714:08
 */
public interface CargoService {

    BaseResult deleteMenu(String id);

    BaseResult menuListPages(PaperListPage paperListPage);

    boolean updateServicePaper(SaveReq req);

    boolean updateMenu(SaveReq req);

    boolean saveServiceOneMeun(String pid, String name,Integer topLevel);

    boolean saveServiceTwoMeun(String pid, String name, Integer topLevel);

    List<PaperType> selectOneMeunList(String pid);

    List<PaperType> selectTwoLikeMeunList(String pid);

    List<PaperType> selectTwoMeunList(String pid);

    List<PaperType> selectSaveOneMeunList(String pid);

    BaseResult saveServiceTwoMeun(String pid);

    boolean saveServicePapaer(SaveReq req);

    BaseResult serviceRange(String oneRef,String twoRef,String current);

    List<PaperType> serviceCountryRange();

    List<Paper> newPaper();

    BaseResult detail(String id);

    BaseResult detailMenu(String id);

    BaseResult listPage(PaperListPage basePageReq);

    boolean savePapaer(String refId, String name, String content);

    boolean delPapaer(String id);

    boolean update(String id, String name, String content);

    BaseResult paperList();


}

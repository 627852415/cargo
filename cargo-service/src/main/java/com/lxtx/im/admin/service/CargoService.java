package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.Paper;
import com.lxtx.im.admin.service.cargo.req.PaperListPage;

import java.util.List;

/**
 * @author Lin hj
 * @title: CargoService
 * @projectName git_work
 * @description: TODO
 * @date 2020/4/1714:08
 */
public interface CargoService {

    List<Paper> newPaper();

    BaseResult detail(String id);

    BaseResult listPage(PaperListPage basePageReq);

    boolean savePapaer(String refId, String name, String content);

    boolean delPapaer(String id);

    boolean update(String id, String name, String content);

    BaseResult paperList();


}

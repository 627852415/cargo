package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.BasePageReq;

/**
 * @author Lin hj
 * @title: CargoService
 * @projectName git_work
 * @description: TODO
 * @date 2020/4/1714:08
 */
public interface CargoService {

    BaseResult aboutList(BasePageReq basePageReq);
    BaseResult paperList();


}

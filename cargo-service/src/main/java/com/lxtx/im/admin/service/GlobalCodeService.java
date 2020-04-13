package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.GlobalCodeListPageReq;
import com.lxtx.im.admin.service.request.GlobalCodeModifyReq;

public interface GlobalCodeService {

    /**
     * 获取国际简码列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(GlobalCodeListPageReq req);

    /**
     * 新增
     *
     * @param req
     * @return
     */
    BaseResult add(GlobalCodeModifyReq req);

    /**
     * 修改
     *
     * @param req
     * @return
     */
    BaseResult modify(GlobalCodeModifyReq req);

    /**
     * 删除
     *
     * @param req
     * @return
     */
    BaseResult delete(GlobalCodeModifyReq req);


}

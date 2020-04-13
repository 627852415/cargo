package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.InviteChangeLogListPageReq;

public interface InviteRelationChangeLogService {

    /**
     * 获取列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(InviteChangeLogListPageReq req);


}

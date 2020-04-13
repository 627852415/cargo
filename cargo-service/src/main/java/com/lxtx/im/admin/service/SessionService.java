package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.UserDevicePageReq;

/**
* @author:   CXM
* @create:   2018-09-05 18:44
*/
public interface SessionService {
    /**
     * 获取在线用户列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(UserDevicePageReq req) throws Exception;

}

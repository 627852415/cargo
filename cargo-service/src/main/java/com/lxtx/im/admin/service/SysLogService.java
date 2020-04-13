package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.SysLog;
import com.lxtx.im.admin.service.request.SysLogListPageReq;

public interface SysLogService {

    /**
     * 获取操作日志列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(SysLogListPageReq req);

    /**
     * 发送监控操作日志消息
     *
     * @param sysLog
     */
    void sendNotice(SysLog sysLog);
}

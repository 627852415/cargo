package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.ExceptionHandleReq;
import com.lxtx.im.admin.service.request.ExceptionRecordReq;

import javax.servlet.http.HttpSession;

/**
 * 错误日志记录模块业务接口
 *
 * @author tangdy
 * @since 2018-08-27
 */
public interface ExceptionRecordService {

    /**
     * 获取异常记录
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult record(ExceptionRecordReq req, HttpSession session);

    /**
     * 异常处理
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult handle(ExceptionHandleReq req, HttpSession session);
}

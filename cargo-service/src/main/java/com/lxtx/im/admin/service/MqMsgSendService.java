package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.MqMsgSendCancelReq;
import com.lxtx.im.admin.service.request.MqMsgSendListPageReq;
import com.lxtx.im.admin.service.request.MqMsgSendRetryReq;

import javax.servlet.http.HttpSession;

/**
 * mq消息发送重试管理
 *
 * @author : CaiRH
 * @since : 2020-01-03
 */
public interface MqMsgSendService {

    BaseResult listPage(MqMsgSendListPageReq req, HttpSession session);

    BaseResult cancel(MqMsgSendCancelReq req, HttpSession session);

    BaseResult retry(MqMsgSendRetryReq req, HttpSession session);
}

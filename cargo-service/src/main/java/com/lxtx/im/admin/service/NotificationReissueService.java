package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.NotificationReissueHandleReq;
import com.lxtx.im.admin.service.request.NotificationReissuePageReq;

import javax.servlet.http.HttpSession;

/**
 * 通知重发
 *
 * @author CaiRH
 * @since 2019-06-12
 */
public interface NotificationReissueService {

    BaseResult listPage(NotificationReissuePageReq req, HttpSession session);

    BaseResult handle(NotificationReissueHandleReq req, HttpSession session);
}

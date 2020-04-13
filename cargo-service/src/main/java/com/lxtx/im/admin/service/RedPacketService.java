package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.FeignAdminReceiveListReq;
import com.lxtx.im.admin.feign.request.FeignAdminSendListReq;
import com.lxtx.im.admin.service.request.AdminReceiveListReq;
import com.lxtx.im.admin.service.request.AdminSendListReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xumf
 * @date 2019/11/25 15:29
 */
public interface RedPacketService {

    BaseResult adminSendList(FeignAdminSendListReq req);

    /**
     * 设置红包发送请求参数
     *
     * @param req {@link AdminSendListReq}
     * @return {@link FeignAdminSendListReq}
     */
    FeignAdminSendListReq getFeignAdminSendListReq(AdminSendListReq req);

    /**
     * 查看详情
     *
     * @param id    红包发送 ID
     * @param model {@link Model}
     * @return url
     */
    String sendDetail(String id, Model model);

    /**
     * 查看详情
     *
     * @param id    红包发送 ID
     * @param model {@link Model}
     * @return url
     */
    String receiveDetail(String id, Model model);

    BaseResult adminReceiveList(FeignAdminReceiveListReq req);

    /**
     * 获取领取红包记录条件
     *
     * @param req {@link AdminReceiveListReq}
     * @return {@link FeignAdminReceiveListReq}
     */
    FeignAdminReceiveListReq getFeignAdminReceiveListReq(AdminReceiveListReq req);

    /**
     * 获取红包发送锁
     *
     * @param req     {@link AdminSendListReq}
     * @param session {@link HttpSession}
     * @return {@link BaseResult}
     */
    BaseResult sendDownloadLock(AdminSendListReq req, HttpSession session);


    /**
     * 获取红包领取锁
     *
     * @param req     {@link AdminReceiveListReq}
     * @param session {@link HttpSession}
     * @return {@link BaseResult}
     */
    BaseResult receiveDownloadLock(AdminReceiveListReq req, HttpSession session);

    /**
     * 红包发送 excel 导出
     *
     * @param req      {@link AdminSendListReq}
     * @param session  {@link HttpSession}
     * @param response {@link HttpServletResponse}
     */
    void sendDownloadList(AdminSendListReq req, HttpSession session, HttpServletResponse response);

    /**
     * 红包领取 excel 导出
     *
     * @param req      {@link AdminReceiveListReq}
     * @param session  {@link HttpSession}
     * @param response {@link HttpServletResponse}
     */
    void receiveDownloadList(AdminReceiveListReq req, HttpSession session, HttpServletResponse response);

}

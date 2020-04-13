package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.PlatformWithdrawApplyAuditReq;
import com.lxtx.im.admin.service.request.PlatformWithdrawApplyDetailReq;
import com.lxtx.im.admin.service.request.PlatformWithdrawApplyListDownloadReq;
import com.lxtx.im.admin.service.request.PlatformWithdrawApplyListReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: 系统提现
 * @author: CXM
 * @create: 2018-08-31 09:55
 **/
public interface PlatformWithdrawApplyService {

    /**
     * 系统提现列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(PlatformWithdrawApplyListReq req);

    /**
     * 系统提现详情
     *
     * @param req
     * @param model
     * @return
     */
    String detail(PlatformWithdrawApplyDetailReq req, Model model);

    /**
     * 平台提款申请审核
     *
     * @param req
     * @return
     */
    BaseResult platformWithdrawApplyAudit(PlatformWithdrawApplyAuditReq req);

    /**
     * 系统提款申请列表下载
     *
     * @param response
     * @param req
     */
    void download(HttpServletResponse response, PlatformWithdrawApplyListDownloadReq req);
}

package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.RebateDetailPageReq;
import com.lxtx.im.admin.service.request.RebateReportListPageReq;
import com.lxtx.im.admin.service.request.RebatePlayerGameDetailPageReq;
import com.lxtx.im.admin.service.request.RebateReportListReq;

import javax.servlet.http.HttpServletResponse;

public interface RebateReportService {

    /**
     * 获取返佣列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(RebateReportListPageReq req);

    BaseResult userRebateDetailList(RebateDetailPageReq req);

    BaseResult playerGameDetail(RebatePlayerGameDetailPageReq req);

    /**
     * 获取返佣列表
     * @param req
     * @return
     */
    BaseResult rebateList(RebateReportListPageReq req);

    /**
     * 获取下级返佣详情
     * @param req
     * @return
     */
    BaseResult rebateDetail(RebateDetailPageReq req);

    /**
     * 返佣列表下载
     * @param response
     * @param req
     */
    void rebateListDownload(HttpServletResponse response, RebateReportListReq req);

    /**
     * 返佣详情下载
     * @param response
     * @param req
     */
    void rebateDetailDownload(HttpServletResponse response, RebateDetailPageReq req);
}

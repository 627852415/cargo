package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.ChargeReportListPageReq;
import com.lxtx.im.admin.service.request.ChargeReportListReq;

import javax.servlet.http.HttpServletResponse;

/**
 * 手续费报表
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
public interface ChargeReportService {


    BaseResult listPage(ChargeReportListPageReq req);

    BaseResult generateReport();

    void exportExcel(HttpServletResponse response, ChargeReportListReq req);

}

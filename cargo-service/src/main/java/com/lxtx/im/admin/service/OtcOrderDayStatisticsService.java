package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.OtcOrderDayStatisticsListPageReq;

import javax.servlet.http.HttpServletResponse;

/**
 * OTC买卖比值报表
 *
 * @Author: liyunhua
 * @Date: 2019/3/7
 */
public interface OtcOrderDayStatisticsService {


    BaseResult listPage(OtcOrderDayStatisticsListPageReq req);


    BaseResult generateReport();

    /**
     * 导出OTC买卖报表
     *
     * @param response
     * @param req
     */
    void exportExcel(HttpServletResponse response, OtcOrderDayStatisticsListPageReq req);
}

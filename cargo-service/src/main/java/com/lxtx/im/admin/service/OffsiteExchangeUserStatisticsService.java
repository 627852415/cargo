package com.lxtx.im.admin.service;

import com.lxtx.im.admin.service.request.ExchangeUserStatisticsListPageReq;
import com.lxtx.im.admin.service.response.OffsiteExchangeUserStatisticsListPageResp;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户换汇统计业务类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 **/
public interface OffsiteExchangeUserStatisticsService {

    /**
     * 用户换汇统计分页查询
     * @param req
     * @return
     */
    OffsiteExchangeUserStatisticsListPageResp listPage(ExchangeUserStatisticsListPageReq req);

    /**
     * 获取币对列表
     * @param model
     */
    void waveRateList(Model model);

    /**
     * 用户换汇统计列表下载
     * @param response
     * @param req
     */
    void exportExcel(HttpServletResponse response, ExchangeUserStatisticsListPageReq req);
}

package com.lxtx.im.admin.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import com.lxtx.im.admin.service.response.RechargeTopgateOrderStatisticsResp;
import org.springframework.ui.Model;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.RechargeTopgateOrderDetailReq;
import com.lxtx.im.admin.service.request.RechargeTopgateOrderDownloadReq;
import com.lxtx.im.admin.service.request.RechargeTopgateOrderPageReq;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-12-17 15:42
 * @Description
 */
public interface RechargeTopgateOrderService {

    BaseResult listPage(RechargeTopgateOrderPageReq req);

    void downloadList(HttpServletResponse response, RechargeTopgateOrderDownloadReq req, HttpSession session);

    String detail(RechargeTopgateOrderDetailReq req, Model model);

    BaseResult downloadLock(RechargeTopgateOrderPageReq req, HttpSession session);

    RechargeTopgateOrderStatisticsResp getStatistics(FeignStatisticsOrderReq req);

    RechargeTopgateOrderStatisticsResp getAllStatistics(boolean refresh);
    /*RechargeTopgateOrderStatisticsResp getStatisticsByDate(StatisticsOrderReq req);*/
}

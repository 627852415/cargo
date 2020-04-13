package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import com.lxtx.im.admin.service.request.OffisteExchangeOrderEndReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeOrderDetailReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeOrderListPageReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeOrderThawBuyerMargin;
import com.lxtx.im.admin.service.response.OffsiteExchangeOrderListPageResp;
import com.lxtx.im.admin.service.response.OffsiteExchangeOrderManageDetail;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: 线下汇换订单接口
 * @author: CXM
 * @create: 2019-04-22 15:04
 */
public interface OffsiteExchangeOrderService {
    /**
     * 获取投诉列表数据
     *
     * @param req
     * @return
     */
    OffsiteExchangeOrderListPageResp listPage(OffsiteExchangeOrderListPageReq req);

    /**
     * 订单列表下载
     *
     * @param response
     * @param req
     */
    void orderDownload(HttpServletResponse response, OffsiteExchangeOrderListPageReq req);

    /**
     * 订单详情
     *
     * @param req
     * @return
     */
    OffsiteExchangeOrderManageDetail detail(OffsiteExchangeOrderDetailReq req);

    /**
     * 结束线下汇换订单交易
     *
     * @param req
     * @return
     */
    BaseResult endOrder(OffisteExchangeOrderEndReq req);

    /**
     * 获取订单统计
     *
     * @return
     */
    BaseResult getOrder(FeignStatisticsOrderReq req);

    /**
     * 获取币对
     * @param model
     */
    void waveRateList(Model model);

    /**
     *
     * @return
     */
    BaseResult thawOffsiteExchangeOrderMargin(OffsiteExchangeOrderThawBuyerMargin req);
}

package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.OffsiteExchangeOrderFeignFallBackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 线下汇换订单
 * @author: CXM
 * @create: 2019-04-22 15:10
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = OffsiteExchangeOrderFeignFallBackFactory.class)
public interface OffsiteExchangeOrderFeign {

    /**
     * 订单列表（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/offsite/merchant/order/manage/listPage")
    BaseResult listPage(FeignOffsiteExchangeOrderListPageReq req);

    /**
     * 订单详情
     *
     * @param req
     * @return
     */
    @PostMapping("/offsite/merchant/order/manage/detail")
    BaseResult detail(FeignOffsiteExchangeOrderDetailReq req);

    /**
     * 结束订单交易
     *
     * @param req
     * @return
     */
    @PostMapping("/offsite/merchant/order/manage/end")
    BaseResult endOrder(FeignOffisteExchangeOrderEndReq req);

    @PostMapping("/offsite/merchant/order/manage/statistics")
    BaseResult getStatisticsOrder(FeignStatisticsOrderReq req);

    @PostMapping("/offsite/merchant/order/manage/thawOffsiteExchangeOrderMargin")
    BaseResult thawOffsiteExchangeOrderMargin(FeignOffsiteExchangeOrderThawBuyerMargin feignReq);
}

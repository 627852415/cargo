package com.lxtx.im.admin.feign.feign;

import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.RechargeTopgateFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignRechargeTopgateOrderDownloadReq;
import com.lxtx.im.admin.feign.request.FeignRechargeTopgateOrderPageReq;
import com.lxtx.im.admin.feign.request.FeignRechargeTopgateOrderDetailReq;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-12-17 16:03
 * @Description
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = RechargeTopgateFeignFallbackFactory.class)
public interface RechargeTopgateFeign {
    @PostMapping(value = "/admin/zbpay/order/listPage")
    BaseResult listPage(FeignRechargeTopgateOrderPageReq req);

    @PostMapping(value = "/admin/zbpay/order/downloadList")
    BaseResult downloadList(FeignRechargeTopgateOrderDownloadReq req);

    @PostMapping(value = "/admin/zbpay/order/detail")
    BaseResult detail(FeignRechargeTopgateOrderDetailReq req);

    @PostMapping(value = "/admin/zbpay/order/manage/statistics")
    BaseResult getStatisticsOrder(FeignStatisticsOrderReq req);

}

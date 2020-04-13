package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.WalletOtcOrderStatisticsFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignOtcOrderDayStatisticsListPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: liyunhua
 * @Date: 2019/3/8
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = WalletOtcOrderStatisticsFallbackFactory.class)
public interface WalletOtcOrderStatisticsFeign {


    @PostMapping(value = "/otc/order/day/statistics/listPage")
    BaseResult listPage(FeignOtcOrderDayStatisticsListPageReq feignReq);

    @PostMapping(value = "/otc/order/day/statistics/generateReport")
    BaseResult generateReport();

}

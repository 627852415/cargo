package com.lxtx.im.admin.feign.feign;

import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.ExchangeMerchantFeignFallbackFactory;

/**
 * @Author: liyunhua
 * @Date: 2019/4/22
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = ExchangeMerchantFeignFallbackFactory.class)
public interface OffsiteExchangeMerchantFeign {


    @PostMapping(value = "/admin/exchange/merchant/list/page")
    BaseResult listPage(FeignExchangeMerchantListPageReq listPageReq);

    @PostMapping(value = "/admin/exchange/merchant/detail")
    BaseResult detail(FeignExchangeMerchantDetailReq feignReq);

    @PostMapping(value = "/admin/exchange/merchant/verify")
    BaseResult verify(FeignExchangeMerchantVerifyReq feignReq);

    @PostMapping(value = "/admin/exchange/merchant/cancel")
    BaseResult cancel(FeignExchangeMerchantCancelReq feignReq);

    @PostMapping(value = "/admin/exchange/merchant/update/status")
    BaseResult updateStatus(FeignExchangeMerchantUpdateStatusReq feignReq);

    @PostMapping(value = "/admin/exchange/merchant/update/merchantwaverate")
    BaseResult updateMerchantWaveRate(FeignOffsiteExchangeMerchantUpdateWaveRateReq feignReq);
    
    @PostMapping(value = "/admin/exchange/merchant/transaction/statistics/list/page")
    BaseResult merchantTransactionStatisticsListPage(FeignExchangeMerchantTransactionStatisticsListPageReq feignReq);

    @PostMapping(value = "/admin/exchange/merchant/goods/statistics/list/page")
    BaseResult merchantGoodsStatisticsListPage(FeignExchangeGoodsListPageReq feignReq);
}

package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.OffsiteExchangeWaveRateFallBackFactory;
import com.lxtx.im.admin.feign.request.*;
import feign.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 换汇汇率
 *
 * @author CaiRH
 * @since 2019-05-24 14:07
*/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = OffsiteExchangeWaveRateFallBackFactory.class)
public interface OffsiteExchangeWaveRateFeign {

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/list/page")
    BaseResult listPage(FeignBasePageReq listPageReq);

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/detail")
    BaseResult detail(FeignOffsiteExchangeWaveRateIdReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/save")
    BaseResult save(FeignOffsiteExchangeWaveRateSaveReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/del")
    BaseResult del(FeignOffsiteExchangeWaveRateIdReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/area/list")
    BaseResult areaRateList(FeignOffsiteExchangeWaveAreaRateListReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/area/update")
    BaseResult areaRateUpdate(FeignOffsiteExchangeWaveRateAreaUpdateReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/realTimeRate")
    BaseResult realTimeRate(FeignOffsiteExchangeRealTimeRateReq req);

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/rebate/list")
    BaseResult rebateList(FeignOffsiteExchangeWaveRateRebateReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/wave/rate/list")
    BaseResult list();
}

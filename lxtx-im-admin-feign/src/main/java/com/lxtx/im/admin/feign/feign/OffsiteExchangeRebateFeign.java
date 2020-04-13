package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.OffsiteExchangeRebateFallBackFactory;
import com.lxtx.im.admin.feign.request.FeignBasePageReq;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeRebateIdReq;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeRebateSaveReq;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 支付方式/返利
 *
 * @author CaiRH
 * @since 2019-05-28
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = OffsiteExchangeRebateFallBackFactory.class)
public interface OffsiteExchangeRebateFeign {

    @PostMapping(value = "/admin/offsite/exchange/rebate/list/page")
    BaseResult listPage(FeignBasePageReq listPageReq);

    @PostMapping(value = "/admin/offsite/exchange/rebate/detail")
    BaseResult detail(FeignOffsiteExchangeRebateIdReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/rebate/save")
    BaseResult save(FeignOffsiteExchangeRebateSaveReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/rebate/del")
    BaseResult del(FeignOffsiteExchangeRebateIdReq feignReq);

    @PostMapping(value = "/admin/offsite/exchange/rebate/getRebateAmount")
    BaseResult getRebateAmount(FeignStatisticsOrderReq feignReq);
}

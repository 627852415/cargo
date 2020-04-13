package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.MerchantDepositFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignMerchantDepositDelReq;
import com.lxtx.im.admin.feign.request.FeignMerchantDepositListPageReq;
import com.lxtx.im.admin.feign.request.FeignMerchantDepositSaveReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: liyunhua
 * @Date: 2019/4/22
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = MerchantDepositFeignFallbackFactory.class)
public interface OffsiteExchangeMerchantDepositFeign {


    @PostMapping(value = "/admin/merchant/deposit/list/page")
    BaseResult listPage(FeignMerchantDepositListPageReq listPageReq);

    @PostMapping(value = "/admin/merchant/deposit/coin/list")
    BaseResult getDepositCoinList();

    @PostMapping(value = "/admin/merchant/deposit/detail")
    BaseResult detail(FeignMerchantDepositSaveReq feignReq);

    @PostMapping(value = "/admin/merchant/deposit/save")
    BaseResult save(FeignMerchantDepositSaveReq feignReq);

    @PostMapping(value = "/admin/merchant/deposit/del")
    BaseResult del(FeignMerchantDepositDelReq feignReq);
}

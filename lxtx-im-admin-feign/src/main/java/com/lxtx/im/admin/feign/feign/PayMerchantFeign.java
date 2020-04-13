package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.PayMerchantFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignPayMerchantDetailReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantListPageReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantUpdateStatusReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantVerifyReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 类商家外部调用
 * @author: CXM
 * @create: 2019-03-11 17:01
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = PayMerchantFeignFallbackFactory.class)
public interface PayMerchantFeign {
    /**
     * 商家列表
     *
     * @param feign
     * @return
     */
    @PostMapping(value = "/merchant/list")
    BaseResult listPage(FeignPayMerchantListPageReq feign);

    /**
     * 更新商家状态
     *
     * @param feign
     * @return
     */
    @PostMapping(value = "/merchant/update/status")
    BaseResult updateStatus(FeignPayMerchantUpdateStatusReq feign);

    @PostMapping(value = "/merchant/detail")
    BaseResult detail(FeignPayMerchantDetailReq feign);

    @PostMapping(value = "/merchant/verify")
    BaseResult verify(FeignPayMerchantVerifyReq feignReq);

}

package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.PayMerchantFeign;
import com.lxtx.im.admin.feign.request.FeignPayMerchantDetailReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantListPageReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantUpdateStatusReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantVerifyReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 商家管理
 * @author: CXM
 * @create: 2019-03-11 15:28
 */
@Component
@Slf4j
@Setter
public class PayMerchantFeignFallback implements PayMerchantFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignPayMerchantListPageReq feign) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult updateStatus(FeignPayMerchantUpdateStatusReq feign) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignPayMerchantDetailReq feign) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult verify(FeignPayMerchantVerifyReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }


}

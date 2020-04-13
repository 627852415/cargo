package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.CoinChargeFeign;
import com.lxtx.im.admin.feign.request.FeignCoinChargeDeleteReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeInfoReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeListPageReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeSaveReq;
import feign.hystrix.FallbackFactory;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: CXM
 * @create: 2018-10-12 14:30
 */
@Component
@Slf4j
@Setter
public class CoinChargeFeignFallback implements CoinChargeFeign{
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignCoinChargeListPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult delete(FeignCoinChargeDeleteReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult saveOrUpdate(FeignCoinChargeSaveReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult info(FeignCoinChargeInfoReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

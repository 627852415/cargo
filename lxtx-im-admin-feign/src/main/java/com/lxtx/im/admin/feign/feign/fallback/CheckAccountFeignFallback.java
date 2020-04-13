package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.CheckAccountFeign;
import com.lxtx.im.admin.feign.request.FeignPlatformAllCoinCheckTimeReq;
import com.lxtx.im.admin.feign.request.PlatNameReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Setter

public class CheckAccountFeignFallback implements CheckAccountFeign {
    private Throwable cause;

    @Override
    public BaseResult getCoinMistakeRecords(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getMistakeAmountAndMistakeCount(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getTrendData(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getAllCoin() {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getPlatformlCoinBatchDetail(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getFlowDetail(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getChartTotalData(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getChartCurrencyData(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getChartDiffData(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getPlatIdById(PlatNameReq id) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE,
                Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

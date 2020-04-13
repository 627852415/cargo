package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.WalletOtcOrderStatisticsFeign;
import com.lxtx.im.admin.feign.request.FeignOtcOrderDayStatisticsListPageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: liyunhua
 * @Date: 2019/3/8
 */
@Component
@Slf4j
@Setter
public class WalletOtcOrderStatisticsFallback implements WalletOtcOrderStatisticsFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignOtcOrderDayStatisticsListPageReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult generateReport() {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

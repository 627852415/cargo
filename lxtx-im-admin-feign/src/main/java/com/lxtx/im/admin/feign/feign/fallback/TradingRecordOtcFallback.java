package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.TradingRecordOtcFeign;
import com.lxtx.im.admin.feign.request.FeignCapitalDetailReq;
import com.lxtx.im.admin.feign.request.FeignCapitalReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordOtcDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordOtcReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
public class TradingRecordOtcFallback implements TradingRecordOtcFeign {

    private Throwable cause;

    @Override
    public BaseResult list(FeignTradingRecordOtcReq feignTradingRecordOtcReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult listPage(FeignTradingRecordOtcReq feignTradingRecordOtcReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignTradingRecordOtcDetailReq feignTradingRecordOtcDetailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

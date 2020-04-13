package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.TradingRecordFastExchangeFeign;
import com.lxtx.im.admin.feign.request.FeignCapitalDetailReq;
import com.lxtx.im.admin.feign.request.FeignCapitalReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordFastExchangeDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordFastExchangeReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xufeifei
 * @since 2019-11-23
 */

@Component
@Slf4j
@Setter
public class TradingRecordFastExchangeFeignFallback implements TradingRecordFastExchangeFeign {
    private Throwable cause;

    @Override
    public BaseResult list(FeignTradingRecordFastExchangeReq feignTradingRecordFastExchangeReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult listPage(FeignTradingRecordFastExchangeReq feignTradingRecordFastExchangeReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignTradingRecordFastExchangeDetailReq feignTradingRecordFastExchangeDetailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

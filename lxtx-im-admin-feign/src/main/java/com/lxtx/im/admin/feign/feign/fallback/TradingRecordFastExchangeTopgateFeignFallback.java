package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.TradingRecordFastExchangeFeign;
import com.lxtx.im.admin.feign.feign.TradingRecordFastExchangeTopgateFeign;
import com.lxtx.im.admin.feign.request.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hechizhi
 * @since 2020-1-3
 */

@Component
@Slf4j
@Setter
public class TradingRecordFastExchangeTopgateFeignFallback implements TradingRecordFastExchangeTopgateFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignTradingRecordFastExchangeTopgateReq feignTradingRecordFastExchangeTopgateReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignTradingRecordFastExchangeTopgateDetailReq feignTradingRecordFastExchangeTopgateDetailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getStatisticsOrder(FeignStatisticsOrderReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.TradingRecordWithdrawTopgateFeign;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordWithdrawTopgateDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordWithdrawTopgateReq;
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
public class TradingRecordWithdrawTopgateFeignFallback implements TradingRecordWithdrawTopgateFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignTradingRecordWithdrawTopgateReq feignTradingRecordWithdrawTopgateReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignTradingRecordWithdrawTopgateDetailReq feignTradingRecordWithdrawTopgateDetailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getStatisticsOrder(FeignStatisticsOrderReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

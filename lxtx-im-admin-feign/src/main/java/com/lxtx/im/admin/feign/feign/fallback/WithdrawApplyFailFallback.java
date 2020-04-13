package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.WithdrawApplyFailFeign;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailRecordDealFailReq;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailRecordDealSuccessReq;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
public class WithdrawApplyFailFallback implements WithdrawApplyFailFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignWithdrawApplyFailReq feignWithdrawApplyFailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult doSuccess(FeignWithdrawApplyFailRecordDealSuccessReq feignWithdrawApplyFailRecordDealSuccessReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult doFail(FeignWithdrawApplyFailRecordDealFailReq feignWithdrawApplyFailRecordDealFailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
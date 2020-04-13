package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeComplaintFeign;
import com.lxtx.im.admin.feign.request.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
* @author:   CXM
* @create:   2019-04-22 15:13
*/
@Component
@Slf4j
@Setter
public class OffsiteExchangeComplaintFallback implements OffsiteExchangeComplaintFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignOffsiteExchangeArbitrationListPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignOffsiteExchangeComplaintDetailReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult saveRecord(FeignOffsiteExchangeComplaintRecordSaveReq feign) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult completed(FeignOffsiteExchangeComplaintCompletedReq feign) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult revocation(FeignOffsiteExchangeComplaintRevocationReq feignReq) {
        log.error("投诉撤销失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}

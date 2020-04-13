package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.ExceptionRecordFeign;
import com.lxtx.im.admin.feign.request.FeignExceptionRecordDetailReq;
import com.lxtx.im.admin.feign.request.FeignExceptionRecordReq;
import com.lxtx.im.admin.feign.request.FeignExceptionRecordStatusReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: tangdy
 * @create: 2018-08-22
 **/
@Component
@Slf4j
@Setter
public class ExceptionRecordFeignFallback implements ExceptionRecordFeign {
    private Throwable cause;

    @Override
    public BaseResult obtainRecordPage(FeignExceptionRecordReq feignExceptionRecordReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult turnStatus(FeignExceptionRecordStatusReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignExceptionRecordDetailReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

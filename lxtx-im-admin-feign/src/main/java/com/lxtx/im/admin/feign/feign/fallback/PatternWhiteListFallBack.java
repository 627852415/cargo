package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.PatternWhiteListFeign;
import com.lxtx.im.admin.feign.request.FeignPatternWhiteListModifyReq;
import com.lxtx.im.admin.feign.request.FeignPatternWhiteListPageReq;
import com.lxtx.im.admin.feign.request.FeignUserPatternReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
public class PatternWhiteListFallBack implements PatternWhiteListFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignPatternWhiteListPageReq feignPatternWhiteListPageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult modifyWhiteList(FeignPatternWhiteListModifyReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult userPattern(FeignUserPatternReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.GlobalCodeFeign;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeByCountryReq;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeListReq;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeModifyReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liyunhua
 * @Date 2018-09-04
 * @Description
 */
@Component
@Slf4j
@Setter
public class GlobalCodeFallback implements GlobalCodeFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignGlobalCodeListReq feignGlobalCodeListReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult list(FeignGlobalCodeByCountryReq feignGlobalCodeByCountryReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult add(FeignGlobalCodeModifyReq feignGlobalCodeModifyReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult modify(FeignGlobalCodeModifyReq feignGlobalCodeModifyReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult delete(FeignGlobalCodeModifyReq feignGlobalCodeModifyReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

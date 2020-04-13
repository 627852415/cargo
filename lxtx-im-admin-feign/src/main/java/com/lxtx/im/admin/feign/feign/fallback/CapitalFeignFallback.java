package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.CapitalFeign;
import com.lxtx.im.admin.feign.request.FeignCapitalDetailReq;
import com.lxtx.im.admin.feign.request.FeignCapitalReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2018-09-27
 */

@Component
@Slf4j
@Setter
public class CapitalFeignFallback implements CapitalFeign {
    private Throwable cause;

    @Override
    public BaseResult list(FeignCapitalReq feignCapitalReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult listPage(FeignCapitalReq feignCapitalReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignCapitalDetailReq feignCapitalDetailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

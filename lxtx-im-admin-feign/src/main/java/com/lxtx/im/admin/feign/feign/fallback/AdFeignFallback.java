package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.AdFeign;
import com.lxtx.im.admin.feign.feign.AdPositionFeign;
import com.lxtx.im.admin.feign.request.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 广告Feign
 *
 * @author map
 */
@Component
@Slf4j
@Setter
public class AdFeignFallback implements AdFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignAdListReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult save(FeignAdSaveReq addReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignAdDetailByIdReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.AppVersionFeign;
import com.lxtx.im.admin.feign.request.FeignAppVersionDeleteReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionListPageReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionSaveReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionSelectReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
public class AppVersionFeignFallback implements AppVersionFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignAppVersionListPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult save(FeignAppVersionSaveReq saveReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult info(FeignAppVersionSelectReq selectReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult delete(FeignAppVersionDeleteReq delReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}

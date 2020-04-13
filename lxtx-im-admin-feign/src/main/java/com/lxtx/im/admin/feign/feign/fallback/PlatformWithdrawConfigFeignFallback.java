package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.PlatformWithdrawConfigFeign;
import com.lxtx.im.admin.feign.request.FeignGameSelectReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawConfigDeleteReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawConfigListReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawConfigSaveReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 系统提现配置列表数据
 *
 * @author CaiRH
 * @since 2018-12-20
 **/
@Component
@Slf4j
@Setter
public class PlatformWithdrawConfigFeignFallback implements PlatformWithdrawConfigFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignPlatformWithdrawConfigListReq listPageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult info(FeignGameSelectReq selectReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult save(FeignPlatformWithdrawConfigSaveReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult delete(FeignPlatformWithdrawConfigDeleteReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

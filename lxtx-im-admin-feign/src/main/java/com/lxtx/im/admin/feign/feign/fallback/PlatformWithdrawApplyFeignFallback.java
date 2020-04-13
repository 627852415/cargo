package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.PlatformWithdrawApplyFeign;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyAuditReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyDetailReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyListDownloadReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyListReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 系统提现
 * @author: CXM
 * @create: 2018-08-31 10:00
 **/
@Component
@Slf4j
@Setter
public class PlatformWithdrawApplyFeignFallback implements PlatformWithdrawApplyFeign {
    private Throwable cause;

    @Override
    public BaseResult detail(FeignPlatformWithdrawApplyDetailReq detailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult platformWithdrawApplyAudit(FeignPlatformWithdrawApplyAuditReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult listPage(FeignPlatformWithdrawApplyListReq listPageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult list(FeignPlatformWithdrawApplyListDownloadReq downloadReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

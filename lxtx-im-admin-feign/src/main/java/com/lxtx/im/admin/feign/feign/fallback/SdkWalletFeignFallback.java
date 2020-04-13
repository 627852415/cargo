package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.SdkWalletFeign;
import com.lxtx.im.admin.feign.request.FeignSdkThirdGameOrderAuditReq;
import com.lxtx.im.admin.feign.request.FeignSdkThirdGameOrderListReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: CXM
 * @create: 2018-11-30 11:35
 */
@Component
@Slf4j
@Setter
public class SdkWalletFeignFallback implements SdkWalletFeign {
    private Throwable cause;

    @Override
    public BaseResult orderListPage(FeignSdkThirdGameOrderListReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult orderAudit(FeignSdkThirdGameOrderAuditReq auditReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

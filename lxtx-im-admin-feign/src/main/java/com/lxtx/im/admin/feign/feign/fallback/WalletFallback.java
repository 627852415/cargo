package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.WalletFeign;
import com.lxtx.im.admin.feign.request.FeignRedPacketCompatibleOldDataReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
public class WalletFallback implements WalletFeign {
    private Throwable cause;

    @Override
    public BaseResult compatibleOldData(FeignRedPacketCompatibleOldDataReq req) {
        log.error("feign compatibleOldData error", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

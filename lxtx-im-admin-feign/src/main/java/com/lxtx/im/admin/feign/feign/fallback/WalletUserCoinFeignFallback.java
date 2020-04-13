package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.WalletUserCoinFeign;
import com.lxtx.im.admin.feign.request.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author liyunhua
 * @Date 2018-09-01 0001
 */
@Component
@Slf4j
@Setter
public class WalletUserCoinFeignFallback implements WalletUserCoinFeign {
    private Throwable cause;

    @Override
    public BaseResult list(FeignUserReq feignUserReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult update(FeignUserCoinUpdateReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult queryBalance(FeignQueryBalanceReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult queryWalletUserCoinListByUserId(FeignWalletUserInfoReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult queryWalletUserByAddr(FeignQueryWalletUserByAddrReq req){
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

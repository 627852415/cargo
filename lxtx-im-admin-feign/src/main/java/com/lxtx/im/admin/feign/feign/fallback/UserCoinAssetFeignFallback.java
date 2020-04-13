package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.UserCoinAssetFeign;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyListDownloadReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinAssetDetailReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinAssetDiffReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinAssetListReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 资金流水
 * @author: CXM
 * @create: 2018-08-31 10:00
 **/
@Component
@Slf4j
@Setter
public class UserCoinAssetFeignFallback implements UserCoinAssetFeign {
    private Throwable cause;

    @Override
    public BaseResult detail(FeignUserCoinAssetDetailReq detailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult listPage(FeignUserCoinAssetListReq listPageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult list(FeignPlatformWithdrawApplyListDownloadReq downloadReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult downloadList(FeignUserCoinAssetListReq listPageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult generateFlowReport(FeignUserCoinAssetDiffReq listPageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

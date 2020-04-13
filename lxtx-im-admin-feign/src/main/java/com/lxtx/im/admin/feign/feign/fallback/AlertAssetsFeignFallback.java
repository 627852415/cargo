package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.AlertAssetsFeign;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsDetailReq;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsReq;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsSaveReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/19 15:16
 */
@Component
@Slf4j
@Setter
public class AlertAssetsFeignFallback implements AlertAssetsFeign {

    private Throwable cause;

    @Override
    public BaseResult listPage(FeignAlertAssetsReq feignAlertAssetsReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult save(FeignAlertAssetsSaveReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult update(FeignAlertAssetsSaveReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getById(FeignAlertAssetsDetailReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.UserCoinDailyListFeign;
import com.lxtx.im.admin.feign.request.FeignUserCoinDailyDetailReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinDailyListDownReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinDailyListReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinDailySnapshotRebuildReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-05-31
 */
@Component
@Slf4j
@Setter
public class UserCoinDailyListFallback implements UserCoinDailyListFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignUserCoinDailyListReq listPageReq) {
        log.error("feign 获取列表数据", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignUserCoinDailyDetailReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult createDetailExcel(FeignUserCoinDailyDetailReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult downData(FeignUserCoinDailyListDownReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult rebuildSnapshot(FeignUserCoinDailySnapshotRebuildReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
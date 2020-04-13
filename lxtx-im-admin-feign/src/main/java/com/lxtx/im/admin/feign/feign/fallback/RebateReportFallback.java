package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.RebateReportFeign;
import com.lxtx.im.admin.feign.request.FeignRebateDetailPageReq;
import com.lxtx.im.admin.feign.request.FeignRebatePlayerGameDetailPageReq;
import com.lxtx.im.admin.feign.request.FeignRebateReportListPageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 游戏返佣报表
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Component
@Slf4j
@Setter
public class RebateReportFallback implements RebateReportFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignRebateReportListPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult userRebateDetailList(FeignRebateDetailPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult playerGameDetail(FeignRebatePlayerGameDetailPageReq feign) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult rebateList(FeignRebateReportListPageReq listPageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult rebateDetail(FeignRebateDetailPageReq feign) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

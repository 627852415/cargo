package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.ChargeReportFeign;
import com.lxtx.im.admin.feign.request.FeignChargeReportListPageReq;
import com.lxtx.im.admin.feign.request.FeignChargeReportListReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 手续费报表
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Component
@Slf4j
@Setter
public class ChargeReportFallback implements ChargeReportFeign {
    private Throwable cause;
    @Override
    public BaseResult listPage(FeignChargeReportListPageReq req) {
        log.error("feign",cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult generateReport() {
        log.error("feign",cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult listAll(FeignChargeReportListReq req) {
        log.error("feign",cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}

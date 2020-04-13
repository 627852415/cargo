package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import org.springframework.stereotype.Component;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.RechargeTopgateFeign;
import com.lxtx.im.admin.feign.request.FeignRechargeTopgateOrderDownloadReq;
import com.lxtx.im.admin.feign.request.FeignRechargeTopgateOrderPageReq;
import com.lxtx.im.admin.feign.request.FeignRechargeTopgateOrderDetailReq;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Setter
@Slf4j
public class RechargeTopgateFeignFallback implements RechargeTopgateFeign {
    private Throwable cause;


    @Override
    public BaseResult listPage(FeignRechargeTopgateOrderPageReq req) {
        log.error("feign Topgate订单列表数据获取失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult downloadList(FeignRechargeTopgateOrderDownloadReq req) {
        log.error("feign Topgate订单数据获取失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignRechargeTopgateOrderDetailReq req) {
        log.error("feign Topgate订单详情获取失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getStatisticsOrder(FeignStatisticsOrderReq req) {
        log.error("feign Topgate订单统计数据获取失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

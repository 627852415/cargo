package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.UserCoinTradeTaskFeign;
import com.lxtx.im.admin.feign.request.FeignUserCoinTradeTaskListPageReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinTradeTaskReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 用户资产交易任务表
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Component
@Slf4j
@Setter
public class UserCoinTradeTaskFallback implements UserCoinTradeTaskFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignUserCoinTradeTaskListPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult reprocessTask(FeignUserCoinTradeTaskReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

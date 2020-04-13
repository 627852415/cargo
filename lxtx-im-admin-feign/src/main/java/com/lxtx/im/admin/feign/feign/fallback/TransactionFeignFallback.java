package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.TransactionFeign;
import com.lxtx.im.admin.feign.request.FeignTransactionDetailReq;
import com.lxtx.im.admin.feign.request.FeignTransactionRechargePageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-11-22
 */

@Component
@Slf4j
@Setter
public class TransactionFeignFallback implements TransactionFeign {

    private Throwable cause;

    @Override
    public BaseResult rechargeListPage(FeignTransactionRechargePageReq feignReq) {
        log.error("feign 调用wallet查询资金入账交易记录分页列表数据异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult rechargeDetail(FeignTransactionDetailReq feignReq) {
        log.error("feign 调用wallet查询资金入账交易记录详情数据异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

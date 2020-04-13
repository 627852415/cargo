package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.PayOrderFeign;
import com.lxtx.im.admin.feign.request.FeignBillListReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Czh
 * Date: 2019-03-12 14:57
 */
@Component
@Slf4j
@Setter
public class PayOrderFeignFallback implements PayOrderFeign {

    private Throwable cause;

    @Override
    public BaseResult payOrderListPage(FeignBillListReq feignReq) {
        log.error("feign 调用payOrderListPage异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}
package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.PayBillCheckRecordFeign;
import com.lxtx.im.admin.feign.request.FeignBillListReq;
import com.lxtx.im.admin.feign.request.FeignPayBillCheckRecordEditReq;
import com.lxtx.im.admin.feign.request.FeignPayBillCheckRecordIndexReq;
import com.lxtx.im.admin.feign.request.FeignPayCheckDetailReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Czh
 * Date: 2019-03-19 11:36
 */
@Component
@Slf4j
@Setter
public class PayBillCheckRecordFeignFallback implements PayBillCheckRecordFeign {

    private Throwable cause;

    @Override
    public BaseResult billListPage(FeignBillListReq feignReq) {
        log.error("feign 调用billListPage异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult listPage(FeignPayBillCheckRecordIndexReq feignPayBillCheckRecordIndexReq) {
        log.error("feign 调用listPage异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult checkDetail(FeignPayCheckDetailReq feignPayCheckDetailReq) {
        log.error("feign 调用checkDetail异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult preEdit(FeignPayBillCheckRecordEditReq feignPayBillCheckRecordEditReq) {
        log.error("feign 调用preEdit异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult editCyleAndRate(FeignPayBillCheckRecordEditReq feignPayBillCheckRecordEditReq) {
        log.error("feign 调用editCyleAndRate异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

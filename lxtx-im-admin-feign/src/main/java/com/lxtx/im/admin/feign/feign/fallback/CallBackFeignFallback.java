package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.CallBackFeign;
import com.lxtx.im.admin.feign.request.FeignFastExchangeNoticeReq;
import com.lxtx.im.admin.feign.request.FeignOtcNoticeReq;
import com.lxtx.im.admin.feign.request.FeignSixMerNoticeReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tangdy
 * @Date 2018-08-29
 * @Description
 */

@Component
@Slf4j
@Setter
public class CallBackFeignFallback implements CallBackFeign {
    private Throwable cause;

    @Override
    public BaseResult sixCallBack(FeignSixMerNoticeReq userReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult processOrderCallBack(FeignOtcNoticeReq feignOtcNoticeReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult fexOrderNotice(FeignFastExchangeNoticeReq fastExchangeNoticeReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

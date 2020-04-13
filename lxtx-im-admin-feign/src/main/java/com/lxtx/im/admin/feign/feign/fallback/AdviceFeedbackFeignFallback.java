package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.AdviceFeedbackFeign;
import com.lxtx.im.admin.feign.request.FeignAdviceFeedbackDetailReq;
import com.lxtx.im.admin.feign.request.FeignAdviceFeedbackPageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/4 17:27
 */
@Component
@Slf4j
@Setter
public class AdviceFeedbackFeignFallback implements AdviceFeedbackFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignAdviceFeedbackPageReq feign) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignAdviceFeedbackDetailReq feignAdviceFeedbackDetailReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult createExcel(FeignAdviceFeedbackPageReq feignAdviceFeedbackPageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.SmsFeign;
import com.lxtx.im.admin.feign.request.FeignSmsListPageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
public class SmsFeignFallback  implements SmsFeign {
    private Throwable cause;

    @Override
    public BaseResult list(FeignSmsListPageReq feignSmsListPageReq) {
        log.error("feign： 调用短信列表失败 ", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);

    }
}

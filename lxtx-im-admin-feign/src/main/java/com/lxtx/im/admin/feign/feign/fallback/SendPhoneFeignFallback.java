package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.im.admin.feign.feign.factory.SendPhoneFeignFallbackFactory;
import com.lxtx.im.admin.feign.feign.SendPhoneFeign;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
public class SendPhoneFeignFallback implements SendPhoneFeign {

    private Throwable cause;

    @Override
    public BaseResult sendBcbCardLoginPassword(FeignSmsSendBcbLoginPWReq feignSmsSendBcbLoginPWReq) {
        log.error("feign 发送bcb官网登录验证码 失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}

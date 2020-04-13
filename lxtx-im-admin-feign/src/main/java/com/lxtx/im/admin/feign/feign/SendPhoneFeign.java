package com.lxtx.im.admin.feign.feign;

import com.lxtx.im.admin.feign.feign.factory.SendPhoneFeignFallbackFactory;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-core", fallbackFactory = SendPhoneFeignFallbackFactory.class)
public interface SendPhoneFeign {

    /**
     * 发送bcb官网登录验证码
     *
     * @param smsSendBcbLoginPWReq
     * @return
     */
    @PostMapping("/sms/send/bcb/loginpassword")
    BaseResult sendBcbCardLoginPassword(FeignSmsSendBcbLoginPWReq feignSmsSendBcbLoginPWReq);
}

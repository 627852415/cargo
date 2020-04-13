package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.SendPhoneFeign;
import com.lxtx.im.admin.feign.feign.fallback.SendPhoneFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Husky
 */
@Component
public class SendPhoneFeignFallbackFactory implements FallbackFactory<SendPhoneFeign> {
    @Override
    public SendPhoneFeign create(Throwable e) {
        SendPhoneFeignFallback feignFallback = new SendPhoneFeignFallback();
        feignFallback.setCause(e);
        return feignFallback;
    }
}
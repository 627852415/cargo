package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.SmsFeign;
import com.lxtx.im.admin.feign.feign.fallback.SmsFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;



/**
 * 短信
 */
@Component
public class SmsFeignFallbackFactory implements FallbackFactory<SmsFeign> {

    @Override
    public SmsFeign create(Throwable cause) {
        SmsFeignFallback fallback = new SmsFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }


}

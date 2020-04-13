package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.fallback.PayOrderFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Czh
 * Date: 2019-03-19 11:40
 */
@Component
public class PayOrderFeignFallbackFactory implements FallbackFactory<PayOrderFeignFallback> {

    @Override
    public PayOrderFeignFallback create(Throwable cause) {
        PayOrderFeignFallback fallback = new PayOrderFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}
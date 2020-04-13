package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.OffsiteExchangeMerchantFeign;
import com.lxtx.im.admin.feign.feign.fallback.OffsiteExchangeMerchantFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: liyunhua
 * @Date: 2019/4/22
 */
@Component
public class ExchangeMerchantFeignFallbackFactory implements FallbackFactory<OffsiteExchangeMerchantFeign> {

    @Override
    public OffsiteExchangeMerchantFeignFallback create(Throwable cause) {
        OffsiteExchangeMerchantFeignFallback fallback = new OffsiteExchangeMerchantFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

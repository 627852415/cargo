package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.PayMerchantFeign;
import com.lxtx.im.admin.feign.feign.fallback.PayMerchantFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:26
 * @Description
 */
@Component
public class PayMerchantFeignFallbackFactory implements FallbackFactory<PayMerchantFeign> {


    @Override
    public PayMerchantFeign create(Throwable cause) {
        PayMerchantFeignFallback fallback = new PayMerchantFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }

}

package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.OffsiteExchangeMerchantDepositFeign;
import com.lxtx.im.admin.feign.feign.fallback.OffsiteExchangeMerchantDepositFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: liyunhua
 * @Date: 2019/4/22
 */
@Component
public class MerchantDepositFeignFallbackFactory implements FallbackFactory<OffsiteExchangeMerchantDepositFeign> {

    @Override
    public OffsiteExchangeMerchantDepositFeignFallback create(Throwable cause) {
        OffsiteExchangeMerchantDepositFeignFallback fallback = new OffsiteExchangeMerchantDepositFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

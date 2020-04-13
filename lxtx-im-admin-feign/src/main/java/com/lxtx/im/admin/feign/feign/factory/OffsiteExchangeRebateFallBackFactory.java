package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.OffsiteExchangeRebateFeign;
import com.lxtx.im.admin.feign.feign.fallback.OffsiteExchangeRebateFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-05-28
*/
@Component
public class OffsiteExchangeRebateFallBackFactory implements FallbackFactory<OffsiteExchangeRebateFeign> {
    @Override
    public OffsiteExchangeRebateFeign create(Throwable throwable) {
        OffsiteExchangeRebateFallback feignFallback = new OffsiteExchangeRebateFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

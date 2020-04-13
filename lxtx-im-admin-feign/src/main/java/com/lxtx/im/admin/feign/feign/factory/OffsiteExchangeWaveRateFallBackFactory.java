package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.OffsiteExchangeWaveRateFeign;
import com.lxtx.im.admin.feign.feign.fallback.OffsiteExchangeWaveRateFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-05-24 14:07
*/
@Component
public class OffsiteExchangeWaveRateFallBackFactory implements FallbackFactory<OffsiteExchangeWaveRateFeign> {
    @Override
    public OffsiteExchangeWaveRateFeign create(Throwable throwable) {
        OffsiteExchangeWaveRateFallback feignFallback = new OffsiteExchangeWaveRateFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

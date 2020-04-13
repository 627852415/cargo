package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.CoinChargeFeign;
import com.lxtx.im.admin.feign.feign.fallback.CoinChargeFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:21
 * @Description
 */
@Component
public class CoinChargeFeignFallbackFactory implements FallbackFactory<CoinChargeFeign> {
    @Override
    public CoinChargeFeign create(Throwable throwable) {
        CoinChargeFeignFallback feignFallback = new CoinChargeFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

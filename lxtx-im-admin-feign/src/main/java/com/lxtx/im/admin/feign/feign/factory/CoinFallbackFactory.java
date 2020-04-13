package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.CoinFeign;
import com.lxtx.im.admin.feign.feign.fallback.CoinFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:22
 * @Description
 */
@Component
public class CoinFallbackFactory implements FallbackFactory<CoinFeign> {
    @Override
    public CoinFeign create(Throwable throwable) {
        CoinFallback feignFallback = new CoinFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

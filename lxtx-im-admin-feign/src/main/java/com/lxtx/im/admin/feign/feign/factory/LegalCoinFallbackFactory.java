package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.LegalCoinFeign;
import com.lxtx.im.admin.feign.feign.fallback.LegalCoinFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:17
 * @Description
 */
@Component
public class LegalCoinFallbackFactory implements FallbackFactory<LegalCoinFeign> {
    @Override
    public LegalCoinFeign create(Throwable throwable) {
        LegalCoinFallback feignFallback = new LegalCoinFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

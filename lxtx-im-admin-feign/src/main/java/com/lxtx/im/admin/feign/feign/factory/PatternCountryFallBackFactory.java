package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.PatternCountryFeign;
import com.lxtx.im.admin.feign.feign.fallback.PatternCountryFallBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:22
 * @Description
 */
@Component
public class PatternCountryFallBackFactory implements FallbackFactory<PatternCountryFeign> {
    @Override
    public PatternCountryFeign create(Throwable throwable) {
        PatternCountryFallBack feignFallback = new PatternCountryFallBack();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

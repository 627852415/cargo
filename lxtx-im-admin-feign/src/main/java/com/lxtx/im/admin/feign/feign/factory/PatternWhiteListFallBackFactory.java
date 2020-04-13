package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.PatternWhiteListFeign;
import com.lxtx.im.admin.feign.feign.fallback.PatternWhiteListFallBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:24
 * @Description
 */
@Component
public class PatternWhiteListFallBackFactory implements FallbackFactory<PatternWhiteListFeign> {
    @Override
    public PatternWhiteListFeign create(Throwable throwable) {
        PatternWhiteListFallBack feignFallback = new PatternWhiteListFallBack();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

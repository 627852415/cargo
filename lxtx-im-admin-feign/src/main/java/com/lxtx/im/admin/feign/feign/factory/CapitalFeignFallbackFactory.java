package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.CapitalFeign;
import com.lxtx.im.admin.feign.feign.fallback.CapitalFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:15
 * @Description
 */
@Component
public class CapitalFeignFallbackFactory implements FallbackFactory<CapitalFeign> {
    @Override
    public CapitalFeign create(Throwable throwable) {
        CapitalFeignFallback feignFallback = new CapitalFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

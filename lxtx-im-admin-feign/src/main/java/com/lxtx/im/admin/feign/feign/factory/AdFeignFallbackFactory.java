package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.AdFeign;
import com.lxtx.im.admin.feign.feign.AdPositionFeign;
import com.lxtx.im.admin.feign.feign.fallback.AdFeignFallback;
import com.lxtx.im.admin.feign.feign.fallback.AdPositionFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author map
 * @Date 2020-2-24
 * @Description
 */
@Component
public class AdFeignFallbackFactory implements FallbackFactory<AdFeign> {

    @Override
    public AdFeign create(Throwable throwable) {
        AdFeignFallback feignFallback = new AdFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

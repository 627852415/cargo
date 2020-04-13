package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.AdPositionFeign;
import com.lxtx.im.admin.feign.feign.fallback.AdPositionFeignFallback;
import com.lxtx.im.admin.feign.feign.fallback.GameFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author xufeifei
 * @Date 2020-2-24
 * @Description
 */
@Component
public class AdPositionFeignFallbackFactory implements FallbackFactory<AdPositionFeign> {

    @Override
    public AdPositionFeign create(Throwable throwable) {
        AdPositionFeignFallback feignFallback = new AdPositionFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

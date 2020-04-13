package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.QuartzFeign;
import com.lxtx.im.admin.feign.feign.fallback.QuartzFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:29
 * @Description
 */
@Component
public class QuartzFeignFallbackFactory implements FallbackFactory<QuartzFeign> {
    @Override
    public QuartzFeign create(Throwable cause) {
        QuartzFeignFallback fallback = new QuartzFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

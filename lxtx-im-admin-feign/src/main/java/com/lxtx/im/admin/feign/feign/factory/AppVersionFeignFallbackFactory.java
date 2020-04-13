package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.AppVersionFeign;
import com.lxtx.im.admin.feign.feign.fallback.AppVersionFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:10
 * @Description
 */
@Component
public class AppVersionFeignFallbackFactory implements FallbackFactory<AppVersionFeign> {
    @Override
    public AppVersionFeign create(Throwable throwable) {
        AppVersionFeignFallback feignFallback = new AppVersionFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

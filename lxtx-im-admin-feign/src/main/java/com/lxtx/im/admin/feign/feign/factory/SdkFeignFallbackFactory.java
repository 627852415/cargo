package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.SdkFeign;
import com.lxtx.im.admin.feign.feign.fallback.SdkFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:34
 * @Description
 */
@Component
public class SdkFeignFallbackFactory implements FallbackFactory<SdkFeign> {
    @Override
    public SdkFeign create(Throwable cause) {
        SdkFeignFallback fallback = new SdkFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }

}

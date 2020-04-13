package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.CheckAccountNameFeign;
import com.lxtx.im.admin.feign.feign.fallback.CheckAccountNameFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:19
 * @Description
 */
@Component
public class CheckAccountNameFeignFallbackFactory implements FallbackFactory<CheckAccountNameFeign> {
    @Override
    public CheckAccountNameFeign create(Throwable throwable) {
        CheckAccountNameFeignFallback feignFallback = new CheckAccountNameFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

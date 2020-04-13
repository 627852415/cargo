package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.CheckAccountFeign;
import com.lxtx.im.admin.feign.feign.fallback.CheckAccountFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:18
 * @Description
 */
@Component
public class CheckAccountFeignFallbackFactory implements FallbackFactory<CheckAccountFeign> {
    @Override
    public CheckAccountFeign create(Throwable throwable) {
        CheckAccountFeignFallback feignFallback = new CheckAccountFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }

}

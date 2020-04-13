package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.CallBackFeign;
import com.lxtx.im.admin.feign.feign.fallback.CallBackFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:14
 * @Description
 */
@Component
public class CallBackFeignFallbackFactory implements FallbackFactory<CallBackFeign> {
    @Override
    public CallBackFeign create(Throwable throwable) {
        CallBackFeignFallback feignFallback = new CallBackFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

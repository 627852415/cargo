package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.AirdropFeign;
import com.lxtx.im.admin.feign.feign.fallback.AirdropFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-11 16:44
 * @Description
 */
@Component
public class AirdropFeignFallbackFactory  implements FallbackFactory<AirdropFeign> {
    @Override
    public AirdropFeign create(Throwable throwable) {
        AirdropFeignFallback feignFallback = new AirdropFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

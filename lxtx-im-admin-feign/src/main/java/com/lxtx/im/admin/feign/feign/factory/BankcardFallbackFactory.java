package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.BankcardFeign;
import com.lxtx.im.admin.feign.feign.fallback.BankcardFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:12
 * @Description
 */
@Component
public class BankcardFallbackFactory implements FallbackFactory<BankcardFeign> {
    @Override
    public BankcardFeign create(Throwable throwable) {
        BankcardFallback feignFallback = new BankcardFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

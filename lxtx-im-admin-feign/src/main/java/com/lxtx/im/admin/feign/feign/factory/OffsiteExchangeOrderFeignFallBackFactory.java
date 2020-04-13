package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.OffsiteExchangeOrderFeign;
import com.lxtx.im.admin.feign.feign.fallback.OffsiteExchangeOrderFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
* @author:   CXM
* @create:   2019-04-23 11:34
*/
@Component
public class OffsiteExchangeOrderFeignFallBackFactory implements FallbackFactory<OffsiteExchangeOrderFeign> {
    @Override
    public OffsiteExchangeOrderFeign create(Throwable throwable) {
        OffsiteExchangeOrderFallback feignFallback = new OffsiteExchangeOrderFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

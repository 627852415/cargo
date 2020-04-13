package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.OffsiteExchangeComplaintFeign;
import com.lxtx.im.admin.feign.feign.fallback.OffsiteExchangeComplaintFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
* @author:   CXM
* @create:   2019-04-23 11:34
*/
@Component
public class OffsiteExchangeComplaintFeignFallBackFactory implements FallbackFactory<OffsiteExchangeComplaintFeign> {
    @Override
    public OffsiteExchangeComplaintFeign create(Throwable throwable) {
        OffsiteExchangeComplaintFallback feignFallback = new OffsiteExchangeComplaintFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

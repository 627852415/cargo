package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TransactionFeign;
import com.lxtx.im.admin.feign.feign.fallback.TransactionFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-11-22
 */
@Component
public class TransactionFeignFallbackFactory implements FallbackFactory<TransactionFeign> {

    @Override
    public TransactionFeign create(Throwable throwable) {
        TransactionFeignFallback feignFallback = new TransactionFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.WithdrawApplyFailFeign;
import com.lxtx.im.admin.feign.feign.fallback.WithdrawApplyFailFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class WithdrawApplyFailFeignFallbackFactory implements FallbackFactory<WithdrawApplyFailFeign> {
    @Override
    public WithdrawApplyFailFeign create(Throwable throwable) {
        WithdrawApplyFailFallback feignFallback = new WithdrawApplyFailFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}
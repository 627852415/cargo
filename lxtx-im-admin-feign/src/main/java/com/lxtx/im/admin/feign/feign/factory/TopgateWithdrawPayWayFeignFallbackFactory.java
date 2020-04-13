package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TopgateWithdrawPayWayFeign;
import com.lxtx.im.admin.feign.feign.fallback.TopgateWithdrawPayWayFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class TopgateWithdrawPayWayFeignFallbackFactory implements FallbackFactory<TopgateWithdrawPayWayFeign> {
    @Override
    public TopgateWithdrawPayWayFeign create(Throwable throwable) {
        TopgateWithdrawPayWayFeignFallback fallback = new TopgateWithdrawPayWayFeignFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}

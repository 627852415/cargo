package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TopgateRechargePayWayFeign;
import com.lxtx.im.admin.feign.feign.fallback.TopgateRechargePayWayFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class TopgateRechargePayWayFeignFallbackFactory implements FallbackFactory<TopgateRechargePayWayFeign> {
    @Override
    public TopgateRechargePayWayFeign create(Throwable throwable) {
        TopgateRechargePayWayFeignFallback fallback = new TopgateRechargePayWayFeignFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}

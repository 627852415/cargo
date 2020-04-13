package com.lxtx.im.admin.feign.feign.factory;

import org.springframework.stereotype.Component;

import com.lxtx.im.admin.feign.feign.RechargeTopgateFeign;
import com.lxtx.im.admin.feign.feign.fallback.RechargeTopgateFeignFallback;

import feign.hystrix.FallbackFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:49
 * @Description
 */
@Component
public class RechargeTopgateFeignFallbackFactory implements FallbackFactory<RechargeTopgateFeign> {
    @Override
    public RechargeTopgateFeign create(Throwable cause) {
    	RechargeTopgateFeignFallback fallback = new RechargeTopgateFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

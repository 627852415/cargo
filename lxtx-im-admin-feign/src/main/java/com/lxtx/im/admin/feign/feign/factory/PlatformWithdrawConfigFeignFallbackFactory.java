package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.PlatformWithdrawConfigFeign;
import com.lxtx.im.admin.feign.feign.fallback.PlatformWithdrawConfigFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:28
 * @Description
 */
@Component
public class PlatformWithdrawConfigFeignFallbackFactory implements FallbackFactory<PlatformWithdrawConfigFeign> {
    @Override
    public PlatformWithdrawConfigFeign create(Throwable cause) {
        PlatformWithdrawConfigFeignFallback fallback = new PlatformWithdrawConfigFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }

}

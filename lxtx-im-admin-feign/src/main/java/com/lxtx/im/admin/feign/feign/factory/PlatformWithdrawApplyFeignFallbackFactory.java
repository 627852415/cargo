package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.PlatformWithdrawApplyFeign;
import com.lxtx.im.admin.feign.feign.fallback.PlatformWithdrawApplyFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:27
 * @Description
 */
@Component
public class PlatformWithdrawApplyFeignFallbackFactory implements FallbackFactory<PlatformWithdrawApplyFeign> {
    @Override
    public PlatformWithdrawApplyFeign create(Throwable cause) {
        PlatformWithdrawApplyFeignFallback fallback = new PlatformWithdrawApplyFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

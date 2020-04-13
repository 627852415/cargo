package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.feign.fallback.WalletUserFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

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
public class WalletUserFeignFallbackFactory implements FallbackFactory<WalletUserFeign> {
    @Override
    public WalletUserFeign create(Throwable cause) {
        WalletUserFeignFallback fallback = new WalletUserFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

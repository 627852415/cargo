package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.WalletOtcFeign;
import com.lxtx.im.admin.feign.feign.fallback.WalletOtcOrderFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:44
 * @Description
 */
@Component
public class WalletOtcOrderFallbackFactory implements FallbackFactory<WalletOtcFeign> {
    @Override
    public WalletOtcFeign create(Throwable cause) {
        WalletOtcOrderFallback fallback = new WalletOtcOrderFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

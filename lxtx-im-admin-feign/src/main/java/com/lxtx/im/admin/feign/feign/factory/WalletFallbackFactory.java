package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.WalletFeign;
import com.lxtx.im.admin.feign.feign.fallback.WalletFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class WalletFallbackFactory implements FallbackFactory<WalletFeign> {
    @Override
    public WalletFeign create(Throwable cause) {
        WalletFallback fallback = new WalletFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

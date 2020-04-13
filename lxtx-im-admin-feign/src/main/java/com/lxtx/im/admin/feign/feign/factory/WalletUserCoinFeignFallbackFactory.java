package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.WalletUserCoinFeign;
import com.lxtx.im.admin.feign.feign.fallback.WalletUserCoinFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:47
 * @Description
 */
@Component
public class WalletUserCoinFeignFallbackFactory implements FallbackFactory<WalletUserCoinFeign> {
    @Override
    public WalletUserCoinFeign create(Throwable cause) {
        WalletUserCoinFeignFallback fallback = new WalletUserCoinFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.SdkWalletFeign;
import com.lxtx.im.admin.feign.feign.fallback.SdkWalletFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:35
 * @Description
 */
@Component
public class SdkWalletFeignFallbackFactory implements FallbackFactory<SdkWalletFeign> {
    @Override
    public SdkWalletFeign create(Throwable cause) {
        SdkWalletFeignFallback fallback = new SdkWalletFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

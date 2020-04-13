package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.WalletNoticeWhiteListFeign;
import com.lxtx.im.admin.feign.feign.fallback.WalletNoticeWhiteListFeignFallback;
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
public class WalletNoticeWhiteListFeignFallbackFactory implements FallbackFactory<WalletNoticeWhiteListFeign> {
    @Override
    public WalletNoticeWhiteListFeign create(Throwable cause) {
        WalletNoticeWhiteListFeignFallback fallback = new WalletNoticeWhiteListFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

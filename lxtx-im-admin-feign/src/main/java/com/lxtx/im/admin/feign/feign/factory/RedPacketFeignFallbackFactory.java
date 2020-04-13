package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.RedPacketFeign;
import com.lxtx.im.admin.feign.feign.fallback.RedPacketFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 红包相关
 *
 * @author xumf
 * @date 2019/11/27 17:24
 */
@Component
public class RedPacketFeignFallbackFactory implements FallbackFactory<RedPacketFeign> {

    @Override
    public RedPacketFeign create(Throwable cause) {
        RedPacketFeignFallback fallback = new RedPacketFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}
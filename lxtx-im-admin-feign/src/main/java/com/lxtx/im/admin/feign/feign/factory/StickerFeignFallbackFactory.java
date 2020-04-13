package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.StickerFeign;
import com.lxtx.im.admin.feign.feign.fallback.StickerFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:40
 * @Description
 */
@Component
public class StickerFeignFallbackFactory implements FallbackFactory<StickerFeign> {
    @Override
    public StickerFeign create(Throwable cause) {
        StickerFeignFallback fallback = new StickerFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

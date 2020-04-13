package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.NoticeBroadCastFeign;
import com.lxtx.im.admin.feign.feign.fallback.NoticeBroadCastFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author PengPai
 * Date: Created in 14:17 2020/2/24
 */
@Component
public class NoticeBroadCastFeignFallbackFactory implements FallbackFactory<NoticeBroadCastFeign> {
    @Override
    public NoticeBroadCastFeign create(Throwable throwable) {
        NoticeBroadCastFeignFallback fallback = new NoticeBroadCastFeignFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}

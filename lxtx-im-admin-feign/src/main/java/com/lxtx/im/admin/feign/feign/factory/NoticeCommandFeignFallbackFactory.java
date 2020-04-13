package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.NoticeCommandFeign;
import com.lxtx.im.admin.feign.feign.fallback.NoticeCommandFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author PengPai
 * Date: Created in 14:00 2020/2/24
 */
@Component
public class NoticeCommandFeignFallbackFactory implements FallbackFactory<NoticeCommandFeign> {
    @Override
    public NoticeCommandFeign create(Throwable throwable) {
        NoticeCommandFeignFallback fallback = new NoticeCommandFeignFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}

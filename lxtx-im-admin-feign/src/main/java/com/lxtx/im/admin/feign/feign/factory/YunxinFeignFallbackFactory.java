package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.YunxinFeign;
import com.lxtx.im.admin.feign.feign.fallback.YunxinFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:51
 * @Description
 */
@Component
public class YunxinFeignFallbackFactory implements FallbackFactory<YunxinFeign> {
    @Override
    public YunxinFeign create(Throwable cause) {
        YunxinFeignFallback fallback = new YunxinFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

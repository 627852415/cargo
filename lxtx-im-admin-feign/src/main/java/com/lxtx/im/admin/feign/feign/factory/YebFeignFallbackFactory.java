package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.YebFeign;
import com.lxtx.im.admin.feign.feign.fallback.YebFeignFallback;
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
public class YebFeignFallbackFactory implements FallbackFactory<YebFeign> {
    @Override
    public YebFeign create(Throwable cause) {
        YebFeignFallback fallback = new YebFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

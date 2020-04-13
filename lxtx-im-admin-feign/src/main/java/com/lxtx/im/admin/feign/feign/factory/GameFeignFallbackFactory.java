package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.GameFeign;
import com.lxtx.im.admin.feign.feign.fallback.GameFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:36
 * @Description
 */
@Component
public class GameFeignFallbackFactory implements FallbackFactory<GameFeign> {

    @Override
    public GameFeign create(Throwable throwable) {
        GameFeignFallback feignFallback = new GameFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

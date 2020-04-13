package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.GlobalCodeFeign;
import com.lxtx.im.admin.feign.feign.fallback.GlobalCodeFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author liyunhua
 * @Date 2018-09-04
 * @Description
 */
@Component

public class GlobalCodeFallbackFactory implements FallbackFactory<GlobalCodeFeign> {
    @Override
    public GlobalCodeFeign create(Throwable throwable) {
        GlobalCodeFallback feignFallback = new GlobalCodeFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

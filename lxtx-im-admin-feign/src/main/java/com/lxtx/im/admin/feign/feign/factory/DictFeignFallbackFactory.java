package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.DictFeign;
import com.lxtx.im.admin.feign.feign.fallback.DictFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:31
 * @Description
 */
@Component
public class DictFeignFallbackFactory implements FallbackFactory<DictFeign> {
    @Override
    public DictFeign create(Throwable throwable) {
        DictFeignFallback feignFallback = new DictFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }

}

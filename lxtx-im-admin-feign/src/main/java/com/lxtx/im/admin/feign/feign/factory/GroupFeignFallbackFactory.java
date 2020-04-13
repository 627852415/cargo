package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.GroupFeign;
import com.lxtx.im.admin.feign.feign.fallback.GroupFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:13
 * @Description
 */
@Component
public class GroupFeignFallbackFactory implements FallbackFactory<GroupFeign> {
    @Override
    public GroupFeign create(Throwable throwable) {
        GroupFeignFallback feignFallback = new GroupFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

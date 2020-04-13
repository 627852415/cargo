package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.InviteRelationChangeLogFeign;
import com.lxtx.im.admin.feign.feign.fallback.InviteRelationChangeLogFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:16
 * @Description
 */
@Component
public class InviteRelationChangeLogFeignFallbackFactory implements FallbackFactory<InviteRelationChangeLogFeign> {
    @Override
    public InviteRelationChangeLogFeign create(Throwable throwable) {
        InviteRelationChangeLogFeignFallback feignFallback = new InviteRelationChangeLogFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

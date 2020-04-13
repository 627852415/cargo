package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.InviteFeign;
import com.lxtx.im.admin.feign.feign.fallback.InviteFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:15
 * @Description
 */
@Component
public class InviteFeignFallbackFactory implements FallbackFactory<InviteFeign> {
    @Override
    public InviteFeign create(Throwable throwable) {
        InviteFeignFallback feignFallback = new InviteFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

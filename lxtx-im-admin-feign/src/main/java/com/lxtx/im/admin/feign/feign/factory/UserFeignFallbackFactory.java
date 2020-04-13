package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.fallback.UserFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:43
 * @Description
 */
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeign> {
    @Override
    public UserFeign create(Throwable cause) {
        UserFeignFallback fallback = new UserFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

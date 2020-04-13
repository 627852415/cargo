package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.UserAuthenticationFeign;
import com.lxtx.im.admin.feign.feign.fallback.UserAuthenticationFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Component
public class UserAuthenticationFeignFallbackFactory implements FallbackFactory<UserAuthenticationFeign> {

    @Override
    public UserAuthenticationFeign create(Throwable cause) {
        UserAuthenticationFeignFallback fallback = new UserAuthenticationFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }

}

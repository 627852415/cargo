package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.UserCoinAssetFeign;
import com.lxtx.im.admin.feign.feign.fallback.UserCoinAssetFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:41
 * @Description
 */
@Component
public class UserCoinAssetFeignFallbackFactory implements FallbackFactory<UserCoinAssetFeign> {
    @Override
    public UserCoinAssetFeign create(Throwable cause) {
        UserCoinAssetFeignFallback fallback = new UserCoinAssetFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

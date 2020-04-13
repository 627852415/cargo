package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.UserCoinTradeTaskFeign;
import com.lxtx.im.admin.feign.feign.fallback.UserCoinTradeTaskFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:42
 * @Description
 */
@Component
public class UserCoinTradeTaskFallbackFactory implements FallbackFactory<UserCoinTradeTaskFeign> {
    @Override
    public UserCoinTradeTaskFeign create(Throwable cause) {
        UserCoinTradeTaskFallback fallback = new UserCoinTradeTaskFallback();
        fallback.setCause(cause);
        return fallback;
    }

}

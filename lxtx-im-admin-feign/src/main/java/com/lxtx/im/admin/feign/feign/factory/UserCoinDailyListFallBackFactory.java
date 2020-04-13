package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.UserCoinDailyListFeign;
import com.lxtx.im.admin.feign.feign.fallback.UserCoinDailyListFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-05-31
 */
@Component
public class UserCoinDailyListFallBackFactory implements FallbackFactory<UserCoinDailyListFeign> {
    @Override
    public UserCoinDailyListFeign create(Throwable throwable) {
        UserCoinDailyListFallback feignFallback = new UserCoinDailyListFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

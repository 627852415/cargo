package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.NotificationReissueFeign;
import com.lxtx.im.admin.feign.feign.fallback.NotificationReissueFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-06-12
 */
@Component
public class NotificationReissueFeignFallbackFactory implements FallbackFactory<NotificationReissueFeign> {
    @Override
    public NotificationReissueFeign create(Throwable throwable) {
        NotificationReissueFeignFallback feignFallback = new NotificationReissueFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

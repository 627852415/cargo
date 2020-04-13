package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.MqMsgSendFeign;
import com.lxtx.im.admin.feign.feign.fallback.MqMsgSendFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2020-01-03
 */
@Component
public class MqMsgSendFeignFallbackFactory implements FallbackFactory<MqMsgSendFeign> {
    @Override
    public MqMsgSendFeign create(Throwable throwable) {
        MqMsgSendFeignFallback feignFallback = new MqMsgSendFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

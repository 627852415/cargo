package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.MessageFeign;
import com.lxtx.im.admin.feign.feign.fallback.MessageFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:19
 * @Description
 */
@Deprecated
@Component
public class MessageFeignFallbackFactory implements FallbackFactory<MessageFeign> {
    @Override
    public MessageFeign create(Throwable throwable) {
        MessageFeignFallback feignFallback = new MessageFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

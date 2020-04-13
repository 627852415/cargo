package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.AdviceFeedbackFeign;
import com.lxtx.im.admin.feign.feign.fallback.AdviceFeedbackFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/4 17:26
 */
@Component
public class AdviceFeedbackFeignFallbackFactory implements FallbackFactory<AdviceFeedbackFeign> {

    @Override
    public AdviceFeedbackFeign create(Throwable cause) {
        AdviceFeedbackFeignFallback fallback = new AdviceFeedbackFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}
package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.ExceptionRecordFeign;
import com.lxtx.im.admin.feign.feign.fallback.ExceptionRecordFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:32
 * @Description
 */
@Component
public class ExceptionRecordFeignFallbackFactory implements FallbackFactory<ExceptionRecordFeign> {
    @Override
    public ExceptionRecordFeign create(Throwable throwable) {
        ExceptionRecordFeignFallback feignFallback = new ExceptionRecordFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.SalaryFeign;
import com.lxtx.im.admin.feign.feign.fallback.SalaryFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:49
 * @Description
 */
@Component
public class SalaryFeignFallbackFactory implements FallbackFactory<SalaryFeign> {
    @Override
    public SalaryFeign create(Throwable cause) {
        SalaryFeignFallback fallback = new SalaryFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

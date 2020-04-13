package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.CommissionFeign;
import com.lxtx.im.admin.feign.feign.fallback.CommissionFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author xufeifei
 * @Date 2020-03-07
 * @Description
 */
@Component
public class CommissionFeignFallbackFactory implements FallbackFactory<CommissionFeign> {

    @Override
    public CommissionFeign create(Throwable throwable) {
        CommissionFeignFallback feignFallback = new CommissionFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

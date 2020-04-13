package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.ChargeReportFeign;
import com.lxtx.im.admin.feign.feign.fallback.ChargeReportFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:17
 * @Description
 */
@Component
public class ChargeReportFallbackFactory implements FallbackFactory<ChargeReportFeign> {
    @Override
    public ChargeReportFeign create(Throwable throwable) {
        ChargeReportFallback feignFallback = new ChargeReportFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

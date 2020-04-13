package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.RebateReportFeign;
import com.lxtx.im.admin.feign.feign.fallback.RebateReportFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:30
 * @Description
 */
@Component
public class RebateReportFallbackFactory implements FallbackFactory<RebateReportFeign> {
    @Override
    public RebateReportFeign create(Throwable cause) {
        RebateReportFallback fallback = new RebateReportFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

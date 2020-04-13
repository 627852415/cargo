package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.StatisticsFeign;
import com.lxtx.im.admin.feign.feign.fallback.StatisticsFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:38
 * @Description
 */
@Component
public class StatisticsFallbackFactory implements FallbackFactory<StatisticsFeign> {
    @Override
    public StatisticsFeign create(Throwable cause) {
        StatisticsFallback fallback = new StatisticsFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.OffsiteExchangeUserStatisticsFeign;
import com.lxtx.im.admin.feign.feign.fallback.OffsiteExchangeUserStatisticsFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户换汇统计
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 **/
@Component
public class OffsiteExchangeUserStatisticsFeignFallbackFactory implements FallbackFactory<OffsiteExchangeUserStatisticsFeign> {

    @Override
    public OffsiteExchangeUserStatisticsFeign create(Throwable cause) {
        OffsiteExchangeUserStatisticsFallback feignFallback = new OffsiteExchangeUserStatisticsFallback();
        feignFallback.setCause(cause);
        return feignFallback;
    }

}

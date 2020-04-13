package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.AssetStatisticsListFeign;
import com.lxtx.im.admin.feign.feign.fallback.AssetStatisticsDailyListFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-05-31
 */
@Component
public class AssetStatisticsDailyListFallBackFactory implements FallbackFactory<AssetStatisticsListFeign> {
    @Override
    public AssetStatisticsListFeign create(Throwable throwable) {
        AssetStatisticsDailyListFallback feignFallback = new AssetStatisticsDailyListFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

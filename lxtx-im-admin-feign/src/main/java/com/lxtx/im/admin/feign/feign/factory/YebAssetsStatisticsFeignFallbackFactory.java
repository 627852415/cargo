package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.YebAssetsStatisticsFeign;
import com.lxtx.im.admin.feign.feign.fallback.YebAssetsStatisticsFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 余额宝资产统计列表 feign调用出错解决工厂
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
@Component
public class YebAssetsStatisticsFeignFallbackFactory implements FallbackFactory<YebAssetsStatisticsFeign> {

    @Override
    public YebAssetsStatisticsFeign create(Throwable throwable) {
        YebAssetsStatisticsFeignFallback fallback = new YebAssetsStatisticsFeignFallback();
        fallback.setCause(throwable);
        return fallback;
    }

}

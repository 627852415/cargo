package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.AlertAssetsFeign;
import com.lxtx.im.admin.feign.feign.fallback.AlertAssetsFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/19 15:15
 */
@Component
public class AlertAssetsFallbackFactory implements FallbackFactory<AlertAssetsFeign> {
    @Override
    public AlertAssetsFeign create(Throwable throwable) {
        AlertAssetsFeignFallback feignFallback = new AlertAssetsFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

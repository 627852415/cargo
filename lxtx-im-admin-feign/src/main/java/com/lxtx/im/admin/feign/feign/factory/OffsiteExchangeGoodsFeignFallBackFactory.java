package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.OffsiteExchangeGoodsFeign;
import com.lxtx.im.admin.feign.feign.fallback.OffsiteExchangeGoodsFeignFallBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 09:49
 * @Description
 */
@Component
public class OffsiteExchangeGoodsFeignFallBackFactory implements FallbackFactory<OffsiteExchangeGoodsFeign> {
    @Override
    public OffsiteExchangeGoodsFeign create(Throwable throwable) {
        OffsiteExchangeGoodsFeignFallBack feignFallback = new OffsiteExchangeGoodsFeignFallBack();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

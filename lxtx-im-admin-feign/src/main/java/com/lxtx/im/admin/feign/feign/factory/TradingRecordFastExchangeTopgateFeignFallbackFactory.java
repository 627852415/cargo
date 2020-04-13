package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TradingRecordFastExchangeFeign;
import com.lxtx.im.admin.feign.feign.TradingRecordFastExchangeTopgateFeign;
import com.lxtx.im.admin.feign.feign.fallback.TradingRecordFastExchangeFeignFallback;
import com.lxtx.im.admin.feign.feign.fallback.TradingRecordFastExchangeTopgateFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author hechizhi
 * @Date 2020-1-3
 * @Description
 */
@Component
public class TradingRecordFastExchangeTopgateFeignFallbackFactory implements FallbackFactory<TradingRecordFastExchangeTopgateFeign> {

    @Override
    public TradingRecordFastExchangeTopgateFeign create(Throwable throwable) {
        TradingRecordFastExchangeTopgateFeignFallback feignFallback = new TradingRecordFastExchangeTopgateFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

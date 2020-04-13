package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.CapitalFeign;
import com.lxtx.im.admin.feign.feign.TradingRecordFastExchangeFeign;
import com.lxtx.im.admin.feign.feign.fallback.CapitalFeignFallback;
import com.lxtx.im.admin.feign.feign.fallback.TradingRecordFastExchangeFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author xufeifei
 * @Date 2019-11-23
 * @Description
 */
@Component
public class TradingRecordFastExchangeFeignFallbackFactory implements FallbackFactory<TradingRecordFastExchangeFeign> {

    @Override
    public TradingRecordFastExchangeFeign create(Throwable throwable) {
        TradingRecordFastExchangeFeignFallback feignFallback = new TradingRecordFastExchangeFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

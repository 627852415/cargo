package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TradingRecordWithdrawTopgateFeign;
import com.lxtx.im.admin.feign.feign.fallback.TradingRecordWithdrawTopgateFeignFallback;
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
public class TradingRecordWithdrawTopgateFeignFallbackFactory implements FallbackFactory<TradingRecordWithdrawTopgateFeign> {

    @Override
    public TradingRecordWithdrawTopgateFeign create(Throwable throwable) {
        TradingRecordWithdrawTopgateFeignFallback feignFallback = new TradingRecordWithdrawTopgateFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

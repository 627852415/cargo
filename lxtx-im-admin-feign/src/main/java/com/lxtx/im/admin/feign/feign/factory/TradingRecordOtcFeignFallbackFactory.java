package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TradingRecordOtcFeign;
import com.lxtx.im.admin.feign.feign.fallback.TradingRecordOtcFallback;
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
public class TradingRecordOtcFeignFallbackFactory  implements FallbackFactory<TradingRecordOtcFeign> {
    @Override
    public TradingRecordOtcFeign create(Throwable cause) {
        TradingRecordOtcFallback fallback=new TradingRecordOtcFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

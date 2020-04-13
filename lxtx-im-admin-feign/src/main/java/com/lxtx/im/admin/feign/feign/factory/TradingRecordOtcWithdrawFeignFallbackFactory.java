package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TradingRecordOtcFeign;
import com.lxtx.im.admin.feign.feign.TradingRecordOtcWithdrawFeign;
import com.lxtx.im.admin.feign.feign.fallback.TradingRecordOtcFallback;
import com.lxtx.im.admin.feign.feign.fallback.TradingRecordOtcWithdrawFallback;
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
public class TradingRecordOtcWithdrawFeignFallbackFactory  implements FallbackFactory<TradingRecordOtcWithdrawFeign> {
    @Override
    public TradingRecordOtcWithdrawFeign create(Throwable cause) {
        TradingRecordOtcWithdrawFallback fallback=new TradingRecordOtcWithdrawFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

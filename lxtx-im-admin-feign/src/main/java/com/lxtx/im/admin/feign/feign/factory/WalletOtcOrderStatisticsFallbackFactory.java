package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.WalletOtcOrderStatisticsFeign;
import com.lxtx.im.admin.feign.feign.fallback.WalletOtcOrderStatisticsFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:46
 * @Description
 */
@Component
public class WalletOtcOrderStatisticsFallbackFactory implements FallbackFactory<WalletOtcOrderStatisticsFeign> {
    @Override
    public WalletOtcOrderStatisticsFeign create(Throwable cause) {
        WalletOtcOrderStatisticsFallback fallback = new WalletOtcOrderStatisticsFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

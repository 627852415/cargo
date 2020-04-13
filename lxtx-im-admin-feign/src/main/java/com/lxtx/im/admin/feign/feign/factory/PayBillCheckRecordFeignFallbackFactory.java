package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.PayBillCheckRecordFeign;
import com.lxtx.im.admin.feign.feign.fallback.PayBillCheckRecordFeignFallback;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Czh
 * Date: 2019-03-12 14:40
 */
@Component
@Slf4j
public class PayBillCheckRecordFeignFallbackFactory implements FallbackFactory<PayBillCheckRecordFeign> {

    @Override
    public PayBillCheckRecordFeign create(Throwable cause) {
        PayBillCheckRecordFeignFallback feignFallback = new PayBillCheckRecordFeignFallback();
        feignFallback.setCause(cause);
        return feignFallback;
    }

}
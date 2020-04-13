package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.BcbBankcardFeign;
import com.lxtx.im.admin.feign.feign.fallback.BcbBankcardFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * BCB银行卡
 *
 * @author : CaiRH
 * @since : 2019-04-23
 */
@Component
public class BcbBankcardFeignFallbackFactory implements FallbackFactory<BcbBankcardFeign> {

    @Override
    public BcbBankcardFeign create(Throwable cause) {
        BcbBankcardFeignFallback fallback = new BcbBankcardFeignFallback();
        fallback.setCause(cause);
        return fallback;
    }


}

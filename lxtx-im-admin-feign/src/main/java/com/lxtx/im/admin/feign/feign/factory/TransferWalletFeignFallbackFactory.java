package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TransferWalletFeign;
import com.lxtx.im.admin.feign.feign.fallback.TransferWalletFeignFallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description
 * @author qing 
 * @date: 2019年11月21日 下午6:49:36
 */
@Component
public class TransferWalletFeignFallbackFactory implements FallbackFactory<TransferWalletFeign> {
    @Override
    public TransferWalletFeign create(Throwable throwable) {
    	TransferWalletFeignFallback feignFallback = new TransferWalletFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}

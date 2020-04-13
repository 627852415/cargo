package com.lxtx.im.admin.feign.feign.factory;

import org.springframework.stereotype.Component;

import com.lxtx.im.admin.feign.feign.ScanPayFeign;
import com.lxtx.im.admin.feign.feign.fallback.ScanPayFeignFallback;

import feign.hystrix.FallbackFactory;

@Component
public class ScanPayFeignFallbackFactory implements FallbackFactory<ScanPayFeign> {

	@Override
	public ScanPayFeign create(Throwable throwable) {
		ScanPayFeignFallback feignFallback = new ScanPayFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
	}

}

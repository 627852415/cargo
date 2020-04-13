package com.lxtx.im.admin.feign.feign.fallback;

import org.springframework.stereotype.Component;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.ScanPayFeign;
import com.lxtx.im.admin.feign.request.FeignScanPayListPageReq;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Setter
public class ScanPayFeignFallback implements ScanPayFeign {

	 private Throwable cause;
	
//	@Override
//	public BaseResult listPage(FeignScanPayReq req) {
//		log.error("feign", cause);
//	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
//	}

}

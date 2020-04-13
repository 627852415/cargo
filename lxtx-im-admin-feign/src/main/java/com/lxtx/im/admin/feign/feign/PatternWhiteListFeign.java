package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.PatternWhiteListFallBackFactory;
import com.lxtx.im.admin.feign.request.FeignPatternWhiteListModifyReq;
import com.lxtx.im.admin.feign.request.FeignPatternWhiteListPageReq;
import com.lxtx.im.admin.feign.request.FeignUserPatternReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-core", fallbackFactory = PatternWhiteListFallBackFactory.class)
public interface PatternWhiteListFeign {

	@PostMapping(value = "/pattern/whiteList/listPage")
	BaseResult listPage(FeignPatternWhiteListPageReq feignPatternWhiteListPageReq);
	
	@PostMapping(value = "/pattern/whiteList/modifyWhiteList")
	BaseResult modifyWhiteList(FeignPatternWhiteListModifyReq req);

	/**
	 *
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/pattern/whiteList/user")
	BaseResult userPattern(FeignUserPatternReq req);

}

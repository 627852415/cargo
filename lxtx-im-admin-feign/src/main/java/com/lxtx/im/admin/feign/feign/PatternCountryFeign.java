package com.lxtx.im.admin.feign.feign;

import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.PatternCountryFallBackFactory;
import com.lxtx.im.admin.feign.request.FeignPatternCountryListReq;
import com.lxtx.im.admin.feign.request.FeignPatternCountryModifyReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-core", fallbackFactory = PatternCountryFallBackFactory.class)
public interface PatternCountryFeign {

	@PostMapping(value = "/pattern/country/listPage")
	BaseResult listPage(FeignPatternCountryListReq feignPatternCountryListReq);
	
	@PostMapping(value = "/pattern/country/modifyPattern")
	BaseResult modifyPatternCountry(FeignPatternCountryModifyReq feignPatternCountryModifyReq);

	/**
	 * 分页获取用户的钱包模式 （用于旧数据迁移,version=1.4.1, 下一期可删除）
	 *
	 * @return
	 * @since 2019-05-09
	 */
	@PostMapping("/pattern/country/userPatterns")
	BaseResult userPatternsPage(Page page);

}

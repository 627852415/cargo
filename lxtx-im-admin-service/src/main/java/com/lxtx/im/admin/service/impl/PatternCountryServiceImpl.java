package com.lxtx.im.admin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.PatternCountryFeign;
import com.lxtx.im.admin.feign.request.FeignPatternCountryListReq;
import com.lxtx.im.admin.feign.request.FeignPatternCountryModifyReq;
import com.lxtx.im.admin.service.PatternCountryService;
import com.lxtx.im.admin.service.request.PatternCountryListPageReq;
import com.lxtx.im.admin.service.request.PatternCountryModifyReq;

@Service
public class PatternCountryServiceImpl implements PatternCountryService {

	@Autowired
	private PatternCountryFeign patternCountryFeign;
	
	@Override
	public BaseResult listPage(PatternCountryListPageReq req) {
		FeignPatternCountryListReq feignPatternCountryListReq = new FeignPatternCountryListReq();
		BeanUtils.copyProperties(req, feignPatternCountryListReq);
		return patternCountryFeign.listPage(feignPatternCountryListReq);
	}

	@Override
	public BaseResult modifyPatternCountry(PatternCountryModifyReq req) {
		FeignPatternCountryModifyReq feignPatternCountryModifyReq = new FeignPatternCountryModifyReq();
		BeanUtils.copyProperties(req, feignPatternCountryModifyReq);
		return patternCountryFeign.modifyPatternCountry(feignPatternCountryModifyReq);
	}

}

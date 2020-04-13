package com.lxtx.im.admin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.PatternWhiteListFeign;
import com.lxtx.im.admin.feign.request.FeignPatternWhiteListModifyReq;
import com.lxtx.im.admin.feign.request.FeignPatternWhiteListPageReq;
import com.lxtx.im.admin.service.PatternWhiteListService;
import com.lxtx.im.admin.service.request.PatternWhiteListModifyReq;
import com.lxtx.im.admin.service.request.PatternWhiteListPageReq;

@Service
public class PatternWhiteListServiceImpl implements PatternWhiteListService {

	@Autowired
	private PatternWhiteListFeign patternWhiteListFeign;
	
	@Override
	public BaseResult listPage(PatternWhiteListPageReq req) {
		FeignPatternWhiteListPageReq feignPatternWhiteListPageReq = new FeignPatternWhiteListPageReq();
		BeanUtils.copyProperties(req, feignPatternWhiteListPageReq);
		return patternWhiteListFeign.listPage(feignPatternWhiteListPageReq);
	}

	@Override
	public BaseResult modifyWhiteList(PatternWhiteListModifyReq req) {
		FeignPatternWhiteListModifyReq feignPatternWhiteListModifyReq = new FeignPatternWhiteListModifyReq();
		BeanUtils.copyProperties(req, feignPatternWhiteListModifyReq);
		return patternWhiteListFeign.modifyWhiteList(feignPatternWhiteListModifyReq);
	}

}

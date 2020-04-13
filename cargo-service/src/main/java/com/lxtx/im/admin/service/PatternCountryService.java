package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.PatternCountryListPageReq;
import com.lxtx.im.admin.service.request.PatternCountryModifyReq;

public interface PatternCountryService {

	/**
	 * 国家及地区币种管理分页列表
	 * @param req
	 * @return
	 */
	BaseResult listPage(PatternCountryListPageReq req);
	
	/**
	    * 修改某个国家的钱包模式
	 * @param req
	 * @return
	 */
	BaseResult modifyPatternCountry(PatternCountryModifyReq req);
}

package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.PatternWhiteListModifyReq;
import com.lxtx.im.admin.service.request.PatternWhiteListPageReq;

public interface PatternWhiteListService {

	/**
	 * 用户白名单管理分页列表
	 * @param req
	 * @return
	 */
	BaseResult listPage(PatternWhiteListPageReq req);
	
	/**
	 * 修改白名单
	 * @param req
	 * @return
	 */
	BaseResult modifyWhiteList(PatternWhiteListModifyReq req);
	
}

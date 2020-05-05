package com.lxtx.framework.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.utils.RequestInfo;
import com.lxtx.framework.common.utils.RequestUtils;

public class LxtxRouterInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		String route = httpServletRequest.getHeader(Constants.ROUTE_STR);
		
		if(StringUtils.isNotBlank(route)) {
			RequestUtils.set(new RequestInfo(route));
		}
		
		return true;
	}
	
}

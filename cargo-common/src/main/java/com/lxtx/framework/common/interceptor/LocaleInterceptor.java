package com.lxtx.framework.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lxtx.framework.common.utils.RequestInfo;
import com.lxtx.framework.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.utils.LocaleUtils;

/**
 * 
 * @author Cherish
 * @date 2018年8月9日
 *
 */
public class LocaleInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		// 拦截app端的请求，设置语言类型
		String local = httpServletRequest.getHeader(Constants.LOCALE_STR);
		if (StringUtils.isBlank(local)) {
			local = Constants.DEFAULT_LANGUAGE;
		}
		LocaleUtils.set(LocaleUtils.getByKey(local));

		// 增加version信息
		String version = httpServletRequest.getHeader(Constants.VERSION_NAME);
		RequestUtils.set(new RequestInfo(version));
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}
}

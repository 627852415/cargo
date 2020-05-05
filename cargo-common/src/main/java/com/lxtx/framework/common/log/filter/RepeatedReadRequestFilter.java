package com.lxtx.framework.common.log.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * filter实现 servletRequest.getInputStream() 重复读取,http
 * body缓存在RepeatedlyReadRequestWrapper的body中。
 *
 * @author zkj
 * @date 2018/8/8
 */
public class RepeatedReadRequestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Do nothing
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		RepeatedReadRequestWrapper requestWrapper = new RepeatedReadRequestWrapper((HttpServletRequest) servletRequest);
		filterChain.doFilter(requestWrapper, servletResponse);
	}

	@Override
	public void destroy() {
		// Do nothing
	}

}

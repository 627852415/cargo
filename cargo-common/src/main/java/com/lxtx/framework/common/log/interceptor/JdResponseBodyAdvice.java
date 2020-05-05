package com.lxtx.framework.common.log.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;

/**
 *
 * 拦截controller请求
 * @author zkj
 * @date 2018/8/8
 */
@ControllerAdvice
public class JdResponseBodyAdvice implements ResponseBodyAdvice {

	private final static Logger logger = LoggerFactory.getLogger(JdResponseBodyAdvice.class);


	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
		String requestId = LoggerContext.getRequestId();
		if (null != requestId) {
			Long spendingTime = getSpendingTime();
			try {

                String uri = request.getURI().getPath();
                String json = JSON.toJSONString(body);
				logger.info("########【请求id】{}【响应URI】{}【响应结果】{}【响应时间】{}ms", requestId, uri,json, spendingTime);
			} catch (Exception e) {
				logger.error("beforeBodyWrite:", e);
			}
		}
		return body;
	}

	private Long getSpendingTime() {
		Long startTime = LoggerContext.getStartTime();
		if (null == startTime) {
			return 0L;
		}
		return System.currentTimeMillis() - startTime;
	}
}

package com.lxtx.framework.common.log.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * 功能描述：把HtttpServletRequest的InputStream写入缓存，支持从缓存重复读取。
 *
 * @author zkj
 * @date 2018/8/8
 */
public class RepeatedReadRequestWrapper extends HttpServletRequestWrapper {

	private static final Logger log = LoggerFactory.getLogger(RepeatedReadRequestWrapper.class);

	/**
	 * input stream 的buffer
	 */
	private  String body;

	/**
	 * @param request
	 *            {@link HttpServletRequest} object.
	 */
	public RepeatedReadRequestWrapper(HttpServletRequest request) {
		super(request);
		try {
			body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
		}catch (IOException e){
			log.error(e.getMessage(),e);
		}
	}

	public RepeatedReadRequestWrapper(HttpServletRequest request,String bodyString) {
		super(request);
		this.body=bodyString;
	}
	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
		return new DelegatingServletInputStream(byteArrayInputStream);
	}

}

package com.lxtx.framework.common.utils.http.soa;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


/**
 * Created by zhihua Chen on 2018-05-10.
 */
public class HttpResponse {

	private int status;
	
	private byte[] bytes;
	
	private String body;

	public int getStatus() {
		return status;
	}

	public HttpResponse setStatus(int status) {
		this.status = status;
		return this;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public HttpResponse setBytes(byte[] bytes) {
		this.bytes = bytes;
		return this;
	}

	public String getBody() {
		if (body != null) {
			return body;
		}
		if (bytes == null) {
			return null;
		}
		body = new String(bytes, StandardCharsets.UTF_8);
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String asText(String charset) {
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}

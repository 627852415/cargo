package com.lxtx.framework.common.utils.http.method;


import com.lxtx.framework.common.utils.http.soa.HttpRequest;

/**
 * Created by zhihua Chen on 2018-05-10.
 */
public class Delete extends HttpRequest {

	public Delete(String url) {
		super(url);
	}

	@Override
	public HttpMethod method() {
		return HttpMethod.DELETE;
	}
}

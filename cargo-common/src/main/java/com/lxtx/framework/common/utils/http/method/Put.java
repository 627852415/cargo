package com.lxtx.framework.common.utils.http.method;

import com.lxtx.framework.common.utils.http.soa.HttpRequest;

/**
 * Created by zhihua Chen on 2018-05-10.
 */
public class Put extends HttpRequest {

	public Put(String url) {
		super(url);
	}

	@Override
	public HttpMethod method() {
		return HttpMethod.PUT;
	}

	
}

package com.lxtx.framework.common.utils.http.utils;

import org.apache.http.conn.routing.HttpRoute;

import java.util.Map;

/**
 * Created by zhihua Chen on 2018-05-10.
 */
public class ConfigParams {

	
	private int maxTotal = 1000;
	
	private int defaultMaxPerRoute = 100;
	
	private String referer;
	
	private int connectTimeout = 1000;
	
	private int socketTimeout = 5000;
	
	private int connectRequestTimeout = 1000;
	
	private Map<HttpRoute, Integer> maxPerRouterMap = null;
	
	public ConfigParams() {
		
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public ConfigParams setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
		return this;
	}

	public int getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}

	public ConfigParams setDefaultMaxPerRoute(int defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
		return this;
	}

	public String getReferer() {
		return referer;
	}

	public ConfigParams setReferer(String referer) {
		this.referer = referer;
		return this;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public ConfigParams setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public ConfigParams setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
		return this;
	}

	public int getConnectRequestTimeout() {
		return connectRequestTimeout;
	}

	public ConfigParams setConnectRequestTimeout(int connectRequestTimeout) {
		this.connectRequestTimeout = connectRequestTimeout;
		return this;
	}

	public Map<HttpRoute, Integer> getMaxPerRouterMap() {
		return maxPerRouterMap;
	}

	public ConfigParams setMaxPerRouterMap(Map<HttpRoute, Integer> maxPerRouterMap) {
		this.maxPerRouterMap = maxPerRouterMap;
		return this;
	}
}

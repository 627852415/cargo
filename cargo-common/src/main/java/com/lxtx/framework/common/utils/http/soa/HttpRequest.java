package com.lxtx.framework.common.utils.http.soa;



import com.lxtx.framework.common.utils.http.method.HttpMethod;
import com.lxtx.framework.common.utils.http.utils.UrlUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by zhihua Chen on 2018-05-10.
 */
public abstract class HttpRequest {

	/**
	 * Base url
	 * */
	private String url;
	
	/**
	 * Http url query(get) parameters
	 * */
	private SortedMap<String, String> urlParamMap;
	
	/**
	 * Http url body(post) parameters
	 * */
	private SortedMap<String, String> bodyParamMap;
	
	/**
	 * Http url header parameters
	 * */
	private SortedMap<String, String> headerParamMap;
	
	/**
	 * Connection timeout in milliseconds
	 */
	private int connectTimeout = -1;
	
	/**
	 * request socket timeout in milliseconds
	 */
	private int socketTimeout = -1;
	
	/**
	 * request connection from connection pool timeout, in milliseconds
	 */
	private int connectionRequestTimeout = -1;
	
	// whether enable automatic response decompression
	private boolean autoDecompression = true;
	
	// whether block empty parameter value
	private boolean blockEmptyValue = true;
	
	// post body entity
	private HttpEntity entity;
	
	// url without query
	private String urlWithoutQuery;
	
	// url for final
	private String finalUrl;
	
	// final post body entity
	private HttpEntity finalEntity;
	
	public HttpRequest(String url) {
		if (StringUtils.isBlank(url)) {
			throw new IllegalArgumentException("url must not be null");
		}
		this.url = url;
		this.urlWithoutQuery = UrlUtils.getBaseUrl(url);
		this.urlParamMap = UrlUtils.getUrlParams(url);
	}
	
	public HttpRequest setHeader(String key, String value) {
		if (StringUtils.isBlank(key)) {
			return this;
		}
		if (headerParamMap == null) {
			headerParamMap = new TreeMap<>();
		}
		headerParamMap.put(key, value);
		return this;
	}
	
	public HttpRequest removeHeader(String key) {
		if (headerParamMap == null || StringUtils.isBlank(key)) {
			return this;
		}
		headerParamMap.remove(key);
		return this;
	}
	
	public SortedMap<String, String> getHeaders() {
		if (headerParamMap == null) {
			return null;
		}
		SortedMap<String, String> copyMap = new TreeMap<>();
		copyMap.putAll(headerParamMap);
		return copyMap;
	}
	
	public SortedMap<String, String> getUrlParams() {
		if (urlParamMap == null) {
			return null;
		}
		SortedMap<String, String> copyMap = new TreeMap<>();
		copyMap.putAll(urlParamMap);
		return copyMap;
	}
	
	public SortedMap<String, String> getBodyParams() {
		if (bodyParamMap == null) {
			return null;
		}
		SortedMap<String, String> copyMap = new TreeMap<>();
		copyMap.putAll(bodyParamMap);
		return copyMap;
	}
	
	public HttpRequest setParam(String key, String value) {
		if (method() == HttpMethod.POST || method() == HttpMethod.PUT) {
			return setBodyParam(key, value);
		}
		else {
			return setUrlParam(key, value);
		}
	}
	
	public HttpRequest setParams(Map<String, String> params) {
		if (method() == HttpMethod.POST || method() == HttpMethod.PUT) {
			return setBodyParams(params);
		}
		else {
			return setUrlParams(params);
		}
	}
	
	public SortedMap<String, String> getAllParams() {
		SortedMap<String, String> paramMap = new TreeMap<>();
		if (urlParamMap != null) {
			paramMap.putAll(urlParamMap);
		}
		if (bodyParamMap != null) {
			paramMap.putAll(bodyParamMap);
		}
		
		return paramMap;
	}
	
	public HttpRequest setUrlParam(String key, String value) {
		if(!validateParam(key, value)) {
			return this;
		}
		if (urlParamMap == null) {
			urlParamMap = new TreeMap<>();
		}
		urlParamMap.put(key, value);
		return this;
	}
	
	public HttpRequest setUrlParams(Map<String, String> params) {
		if (params == null || params.isEmpty()) {
			return this;
		}
		for (Map.Entry<String, String> entity : params.entrySet()) {
			setUrlParam(entity.getKey(), entity.getValue());
		}
		return this;
	}
	
	public HttpRequest setBodyParam(String key, String value) {
		if(!validateParam(key, value)) {
			return this;
		}
		if (bodyParamMap == null) {
			bodyParamMap = new TreeMap<>();
		}
		bodyParamMap.put(key, value);
		return this;
	}
	
	public HttpRequest setBodyParams(Map<String, String> params) {
		if (params == null || params.isEmpty()) {
			return this;
		}
		for (Map.Entry<String, String> entity : params.entrySet()) {
			setBodyParam(entity.getKey(), entity.getValue());
		}
		return this;
	}
	
	public void removeParam(String key) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		if (urlParamMap != null) {
			urlParamMap.remove(key);
		}
		if (bodyParamMap != null) {
			bodyParamMap.remove(key);
		}
	}
	
	public String getParam(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		String value = null;
		if (urlParamMap != null) {
			value = urlParamMap.get(key);
		}
		if (value == null && bodyParamMap != null) {
			value = bodyParamMap.get(key);
		}
		return value;
	}

	public String getUrl() {
		return url;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public HttpRequest setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public HttpRequest setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
		return this;
	}

	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public HttpRequest setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
		return this;
	}

	public boolean isAutoDecompression() {
		return autoDecompression;
	}

	public HttpRequest setAutoDecompression(boolean autoDecompression) {
		this.autoDecompression = autoDecompression;
		return this;
	}

	public boolean isBlockEmptyValue() {
		return blockEmptyValue;
	}

	public HttpRequest setBlockEmptyValue(boolean blockEmptyValue) {
		this.blockEmptyValue = blockEmptyValue;
		return this;
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public HttpRequest setEntity(HttpEntity entity) {
		this.entity = entity;
		return this;
	}
	
	public String getFinalUrl() {
		return finalUrl;
	}

	public HttpEntity getFinalEntity() {
		return finalEntity;
	}
	
	public void prepare() {
		if (StringUtils.isBlank(urlWithoutQuery)) {
			throw new IllegalArgumentException("");
		}
		
		try {
			URIBuilder builder = new URIBuilder(urlWithoutQuery);
			if (urlParamMap != null && urlParamMap.size() > 0) {
				for (Map.Entry<String, String> entry : urlParamMap.entrySet()) {
					builder.addParameter(entry.getKey(), entry.getValue());
				}
			}
			this.finalUrl = builder.toString();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Illegal url " + e.getMessage() + url);
		}
		
		this.finalEntity = this.entity;
		if (finalEntity == null && bodyParamMap != null && bodyParamMap.size() > 0) {
			if (method() == HttpMethod.POST || method() == HttpMethod.PUT) {
				List<NameValuePair> params = new ArrayList<>(bodyParamMap.size());
				for (Map.Entry<String, String> entity : bodyParamMap.entrySet()) {
					params.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
				}
				try {
					this.finalEntity = new UrlEncodedFormEntity(params, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					throw new IllegalArgumentException("Illegal body params: " + e.getMessage());
				}
			}
		}
	}
	
	/**
	 * 验证参数
	 */
	private boolean validateParam(String key, String value) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		if (blockEmptyValue && StringUtils.isBlank(value)) {
			return false;
		}
		return true;
	}
	
	public abstract HttpMethod method();
}

package com.lxtx.framework.common.utils.http.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by zhihua Chen on 2018-05-10.
 */
public class UrlUtils {

	/**
	 * get url domain and project
	 */
	public static String getBaseUrl(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}

		int index = url.indexOf("?");
		if (index == -1 || url.endsWith("?")) {
			return url;
		}
		
		return url.substring(0, index); 
	}
	
	/**
	 * Get parameters from url
	 */
	public static SortedMap<String, String> getUrlParams(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		
		int index = url.indexOf("?");
		if (index == -1 || url.endsWith("?")) {
			return null;
		}
		
		String data = url.substring(index + 1);
		if (StringUtils.isBlank(data)) {
			return null;
		}
		
		String[] tokens = data.split("&");
		SortedMap<String, String> paramMap = new TreeMap<>();
		for (String token : tokens) {
			int eIndex = token.indexOf("=");
			if (eIndex == -1) {
				continue;
			}
			String name = token.substring(0, eIndex);
			String value = token.substring(eIndex + 1).trim();
			if (StringUtils.isBlank(value)) {
				continue;
			}
			try {
				value = URLDecoder.decode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			paramMap.put(name, value);
		}
		return paramMap;
	}
}

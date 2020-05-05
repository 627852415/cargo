package com.lxtx.framework.common.utils.http.utils;

import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhihua Chen on 2018-05-10.
 */
public class HttpInvokerConfig {

	private static final Logger logger = LoggerFactory.getLogger(HttpInvokerConfig.class);
	
	private static final String CONFIG = "/http-invoker.properties";
	
	private static final String FRAMEWORK = "/framework-http-invoker.properties";
	
	private volatile ConfigParams configParams;
	
	public HttpInvokerConfig() {
		init();
	}
	
	public void init() {
		InputStream in = HttpInvokerConfig.class.getResourceAsStream(CONFIG);
		if (in == null) {
			in = HttpInvokerConfig.class.getResourceAsStream(FRAMEWORK);
			logger.info("Load http invoker config file is " + FRAMEWORK);
		}
		this.configParams = loadConfigParams(in);
	}

	private ConfigParams loadConfigParams(InputStream in) {
		Properties properties = new Properties();
		try {
			properties.load(in);
			
			ConfigParams params = new ConfigParams();
			params.setConnectRequestTimeout(Integer.valueOf(properties.getProperty("connectRequestTimeout")));
			params.setConnectTimeout(Integer.valueOf(properties.getProperty("connectTimeout")));
			params.setDefaultMaxPerRoute(Integer.valueOf(properties.getProperty("defaultMaxPerRoute")));
			params.setMaxTotal(Integer.valueOf(properties.getProperty("maxTotal")));
			params.setReferer(properties.getProperty("referer"));
			params.setSocketTimeout(Integer.valueOf(properties.getProperty("socketTimeout")));
			
			Map<HttpRoute, Integer> maxPerRoute = parseRouteMap(properties);
			params.setMaxPerRouterMap(maxPerRoute);

			return params;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("Error:close http-config propertis file", e);
				}
			}
		}
	}

	private Map<HttpRoute, Integer> parseRouteMap(Properties properties) {
		Map<HttpRoute, Integer> maxPerRoute = new HashMap<>();
		for (Object key : properties.keySet()) {
			String strKey = (String) key;
			if (strKey.startsWith("route.")) {
				String strRoute = properties.getProperty(strKey);
				String[] splitRoute = strRoute.split(":");
				HttpHost host = new HttpHost(splitRoute[0], Integer.parseInt(splitRoute[1]));
				HttpRoute route = new HttpRoute(host);
				maxPerRoute.put(route, Integer.parseInt(splitRoute[2]));
			}
		}
		
		return maxPerRoute;
	}

	public ConfigParams getConfigParams() {
		return configParams;
	}
}

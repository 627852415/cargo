package com.lxtx.framework.common.utils.http.soa;


import com.alibaba.fastjson.JSON;
import com.lxtx.framework.common.utils.http.utils.ConfigParams;
import com.lxtx.framework.common.utils.http.utils.HttpInvokerConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * Created by zhihua Chen on 2018-05-10.
 */
public class HttpInvoker {

	private static final Logger logger = LoggerFactory.getLogger(HttpInvoker.class);

	private static PoolingHttpClientConnectionManager connManager;
	
	private static CloseableHttpClient httpClient;
	
	private static HttpInvokerConfig invokerConfig = new HttpInvokerConfig();
	
	static {
		httpClient = createSSLClientDefault();
	}


	public static CloseableHttpClient createSSLClientDefault() {


		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
		ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSF);

		//指定信任密钥存储对象和连接套接字工厂
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			//信任任何链接
			TrustStrategy anyTrustStrategy = new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			};
			SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy).build();
			LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			registryBuilder.register("https", sslSF);
		} catch (KeyStoreException e) {
			throw new RuntimeException(e);
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		Registry<ConnectionSocketFactory> registry = registryBuilder.build();

		ConfigParams params = invokerConfig.getConfigParams();

		connManager = new PoolingHttpClientConnectionManager(registry);
		connManager.setMaxTotal(params.getMaxTotal());
		connManager.setDefaultMaxPerRoute(params.getDefaultMaxPerRoute());

		/**
		 * socket配置
		 */
		SocketConfig socketConfig = SocketConfig.custom()
				.setTcpNoDelay(true)     //是否立即发送数据，设置为true会关闭Socket缓冲，默认为false
				.setSoReuseAddress(true) //是否可以在一个进程关闭Socket后，即使它还没有释放端口，其它进程还可以立即重用端口
				.setSoTimeout(10000)       //接收数据的等待超时时间，单位ms
				.setSoLinger(60)         //关闭Socket时，要么发送完所有数据，要么等待60s后，就关闭连接，此时socket.close()是阻塞的
				.setSoKeepAlive(true)    //开启监视TCP连接是否有效
				.build();
		connManager.setDefaultSocketConfig(socketConfig);

		Map<HttpRoute, Integer> maxPerRouteMap = params.getMaxPerRouterMap();
		for (Map.Entry<HttpRoute, Integer> entry : maxPerRouteMap.entrySet()) {
			HttpRoute route = entry.getKey();
			Integer max = entry.getValue();
			connManager.setMaxPerRoute(route, max);
		}
		return HttpClientBuilder.create().setUserAgent("Apache-HttpClient").setConnectionManager(connManager).build();

	}

	public static HttpResponse execute(HttpRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("http request is not be null");
		}
		
		ConfigParams params = invokerConfig.getConfigParams();
		HttpUriRequest uriRequest = transformRequest(request, params);
		
		logger.info("request:" + uriRequest);
		Header[] headers =  uriRequest.getAllHeaders();
		String hearStr = "";
		for(Header h:headers){
			hearStr+=h.getName()+":"+h.getValue()+";";
		}

		logger.info("headerss:" + hearStr);
		logger.info("getFinalEntity:" + JSON.toJSONString(request.getFinalEntity()));
		CloseableHttpResponse closeableResponse = null;
		HttpResponse response = null;
		try {
			closeableResponse = httpClient.execute(uriRequest);
			response = transformResponse(closeableResponse);
			return response;
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		} 
		finally {
			if (closeableResponse != null) {
				try {
					closeableResponse.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
	
	private static HttpResponse transformResponse(CloseableHttpResponse closeableResponse) throws IOException {
		HttpResponse response = new HttpResponse();
		int status = closeableResponse.getStatusLine().getStatusCode();
		response.setStatus(status);
		
		HttpEntity entity = closeableResponse.getEntity();
		if (entity != null) {
			byte[] bytes = EntityUtils.toByteArray(entity);
			response.setBytes(bytes);
		}
		return response;
	}


	private static HttpUriRequest transformRequest(HttpRequest request, ConfigParams params) {
		// prepare request
		request.prepare();
		RequestBuilder builder = RequestBuilder.create(request.method().name());
		
		// prepare url parameters
		builder.setUri(request.getFinalUrl());
		
		// prepare post body
		if (request.getFinalEntity() != null) {
			builder.setEntity(request.getFinalEntity());
		}
		
		builder.addHeader("Accept-Encoding", "gzip, deflate");
		builder.addHeader("X-Forwarded-Fox", "");
		if (StringUtils.isNotBlank(params.getReferer())) {
			builder.addHeader("Referer", params.getReferer());
		}
		
		Map<String, String> headerMap = request.getHeaders();
		if(headerMap != null && headerMap.size() > 0) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				builder.addHeader(entry.getKey(), entry.getValue());
			}
		}
		RequestConfig requestConfig = getRequestConfig(request, params);
		builder.setConfig(requestConfig);
		
		return builder.build();
	}


	private static RequestConfig getRequestConfig(HttpRequest request, ConfigParams params) {
		RequestConfig.Builder bilder = RequestConfig.custom();
		bilder.setConnectionRequestTimeout(params.getConnectRequestTimeout());
		bilder.setConnectTimeout(params.getConnectTimeout()).setRedirectsEnabled(true);
		bilder.setSocketTimeout(params.getSocketTimeout());
		if (request.getConnectionRequestTimeout() > 0) {
			bilder.setConnectionRequestTimeout(request.getConnectionRequestTimeout());
		}
		if (request.getConnectTimeout() > 0) {
			bilder.setConnectTimeout(request.getConnectTimeout());
		}
		if (request.getSocketTimeout() > 0) {
			bilder.setSocketTimeout(request.getSocketTimeout());
		}
        logger.info("http ConfigParams,[{}]",JSON.toJSONString(params));
		return bilder.build();
	}
}

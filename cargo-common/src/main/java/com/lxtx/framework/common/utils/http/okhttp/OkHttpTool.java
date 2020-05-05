package com.lxtx.framework.common.utils.http.okhttp;

import java.io.File;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.exception.LxtxException;
import com.lxtx.framework.common.utils.http.method.RequestTypeEnum;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 
 * @Description OkHttp通用封装
 * @author qing 
 * @date: 2019年12月11日 上午11:04:42
 */
@Slf4j
public class OkHttpTool {
	
	/**
	 * 表单
	 */
	public static final MediaType CONTENT_TYPE_URLENCODE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

	/**
	 * json格式
	 */
	public static final MediaType CONTENT_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
	/**
	 * 文件格式
	 */
	public static final MediaType CONTENT_TYPE_STREAM = MediaType.parse("application/octet-stream; charset=utf-8");
	
	/**
	 * 文本
	 */
	public static final MediaType CONTENT_TYPE_TEXT = MediaType.parse("text/plain; charset=utf-8");
	
	

	//连接超时
	private int connectionTimeoutMillis;
	
	//读超时
	private int readTimeoutMillis;
	
	//cookie保持
	private boolean cookie;

	private OkHttpClient client;
	
	public OkHttpTool() {
		this.connectionTimeoutMillis = 60 * 1000;
		this.readTimeoutMillis = 60 * 1000;
		this.cookie = false;
		initOkHttpClient();
	}
	
	/**
	 * 
	 * @param connectionTimeoutMillis 连接超时
	 * @param readTimeoutMillis 读超时
	 * @param cookie cookie保持
	 */
	public OkHttpTool(int connectionTimeoutMillis, int readTimeoutMillis, boolean cookie) {
		this.connectionTimeoutMillis = connectionTimeoutMillis;
		this.readTimeoutMillis = connectionTimeoutMillis;
		this.cookie = cookie;
		initOkHttpClient();
	}

	/**
	 * 
	 * @Description 初始化OkHttp
	 * @return
	 */
	private void initOkHttpClient() {
		if (client == null) {
			try {
				TrustManagerFactory trustManagerFactory = TrustManagerFactory
						.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				trustManagerFactory.init((KeyStore) null);
				TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
				if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
					throw new IllegalStateException(
							"Unexpected default trust managers:" + Arrays.toString(trustManagers));
				}
				X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, new TrustManager[] { trustManager }, null);
				SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
				okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
				if(cookie) {
					builder.cookieJar(getCookieJar());
				}
				client = builder
						.connectTimeout(connectionTimeoutMillis, TimeUnit.MILLISECONDS)
						.readTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS)
					    .sslSocketFactory(sslSocketFactory, trustManager)
					    .build();
			} catch (Exception e) {
				log.error("请求异常", e);
			}
		}
	}

	/**
	 * 
	 * @Description Cookie管理器
	 * @return
	 */
	private CookieJar getCookieJar() {
		// 初始化Cookie管理器
		CookieJar cookieJar = new CookieJar() {
			private final Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();

			@Override
			public List<Cookie> loadForRequest(HttpUrl httpUrl) {
				List<Cookie> cookiesList = cookiesMap.get(httpUrl.host());
				// 注：这里不能返回null，否则会报NULLException的错误。
				// 原因：当Request 连接到网络的时候，OkHttp会调用loadForRequest()
				return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
			}

			@Override
			public void saveFromResponse(HttpUrl httpUrl, List<Cookie> cookies) {
				// 移除相同的url的Cookie
				String host = httpUrl.host();
				List<Cookie> cookiesList = cookiesMap.get(host);
				if (cookiesList != null) {
					cookiesMap.remove(host);
				}
				// 再重新添加
				cookiesMap.put(host, cookies);
				log.info("{}:{},{}", host, JSON.toJSONString(cookies), JSON.toJSONString(cookiesMap));
			}
		};
		return cookieJar;
	}

	/**
	 * 
	 * @Description 获取并存储Cookie
	 * @param request
	 * @param response
	 * @return
	 */
	public List<Cookie> getCookiesAndSave(Request request, Response response) {
		HttpUrl url = request.url();
		// 获取返回数据的头部
		Headers headers = response.headers();
		// 获取头部的Cookie,注意：可以通过Cooke.parseAll()来获取
		List<Cookie> cookies = Cookie.parseAll(url, headers);
		log.info("{}:{}", url, JSON.toJSONString(cookies));
		// 防止header没有Cookie的情况
		if (!CollectionUtils.isEmpty(cookies)) {
			// 将Cookie存储到缓存
			if (cookie && client != null) {
				client.cookieJar().saveFromResponse(url, cookies);
			}
		}
		// 存储到Cookie管理器中
		return cookies;
	}
	
	/**
	 * 
	 * @Description 通用类型
	 * @param url
	 * @param headerParams
	 * @param queryParams
	 * @param bodyParams
	 * @param requestType
	 * @param mediaType
	 * @return
	 * @throws Exception 
	 */
	public String common(String url, Object headerParams, Object queryParams, Object bodyParams, RequestTypeEnum requestType, MediaType mediaType) throws Exception {
		Request request = null;
		Response response = null;
		try {
			Builder builder = getBuilder(url, headerParams, queryParams);
			if(requestType==null) {
				requestType = RequestTypeEnum.GET;
			}
			if(RequestTypeEnum.GET.equals(requestType)) {
				request = builder.get().build();
			}
			if(RequestTypeEnum.POST.equals(requestType)) {
				RequestBody body = getRequestBody(mediaType, bodyParams);
				request = builder.post(body).build();
			}
			if(RequestTypeEnum.PUT.equals(requestType)) {
				RequestBody body = getRequestBody(mediaType, bodyParams);
				request = builder.put(body).build();
			}
			if(RequestTypeEnum.DELETE.equals(requestType)) {
				if(bodyParams==null) {
					request = builder.delete().build();
				}else {
					RequestBody body = getRequestBody(mediaType, bodyParams);
					request = builder.delete(body).build();
				}
			}
			//得到Response
			response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String resp = response.body().string();
				log.info("url：{},resp:{}", url, resp);
				return resp;
			}
			log.error("url：{},resp:{},code:{}", url, response.message(),response.code());
			throw LxtxException.newException(Constants.CODE_ERROR,response.message());
		}catch (Exception e) {
			log.error("common 请求异常", e);
			throw LxtxException.newException(Constants.CODE_ERROR,e.getMessage());
		} finally {
			if(response!=null) {
				response.close();
			}
		}
	}
	
	/**
	 * 
	 * @Description Builder预处理
	 * @param url
	 * @param headerParams
	 * @param queryParams
	 * @return
	 */
	private Builder getBuilder(String url, Object headerParams,  Object queryParams) {
		StringBuilder host = new StringBuilder(url);
		Builder builder = new Request.Builder();
		// 处理query参数
		if(queryParams!=null) {
			JSONObject queryParamsMap = JSON.parseObject(JSON.toJSONString(queryParams));
			if (!CollectionUtils.isEmpty(queryParamsMap))
				queryParamsMap.forEach((key, value) -> {
					host.append(paramsJoint(host)).append(key).append("=").append(objectToString(value));
				});
		}
		builder.url(host.toString());
		if(headerParams!=null) {
			JSONObject headerParamsMap = JSON.parseObject(JSON.toJSONString(headerParams));
			// 处理请求头
			if (!CollectionUtils.isEmpty(headerParamsMap))
				headerParamsMap.forEach((key, value) -> {
					builder.addHeader(key,objectToString(value));
				});
		}
		return builder;
	}
	
	/**
	 * 
	 * @Description body参数处理
	 * @param mediaType
	 * @param bodyParams
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private RequestBody getRequestBody(MediaType mediaType, Object bodyParams){
		RequestBody body = null;
		// 表单提交
		if (CONTENT_TYPE_URLENCODE.equals(mediaType)) {
			FormBody.Builder formBuilder = new FormBody.Builder();
			if(bodyParams!=null) {
				JSONObject bodyParamsMap = JSON.parseObject(JSON.toJSONString(bodyParams));
				if (!CollectionUtils.isEmpty(bodyParamsMap))
					bodyParamsMap.forEach((key, value) -> {
						formBuilder.add(key,objectToString(value));
					});
			}
			body = formBuilder.build();
		}
		//文件和混合参数请求
		if (CONTENT_TYPE_STREAM.equals(mediaType)) {
			MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
			if(bodyParams!=null) {
				JSONObject bodyParamsMap = JSON.parseObject(JSON.toJSONString(bodyParams));
				if (!CollectionUtils.isEmpty(bodyParamsMap))
					bodyParamsMap.forEach((key, value) -> {
						if (value instanceof File) {
							// addPart用来传入文件参数
							multipartBuilder.addPart(
								Headers.of("Content-Disposition",
								String.format("form-data; name=\"%s\";filename=\"%s\""), key,((File) value).getName()),
								RequestBody.Companion.create(((File) value), CONTENT_TYPE_STREAM)
							);
						} else {
							// addFormDataPart用来传入正常参数
							multipartBuilder.addFormDataPart(key,objectToString(value));
						}
					});
			}
			
			body = multipartBuilder.build();
		}
		// json方式
		if (CONTENT_TYPE_JSON.equals(mediaType) || body == null) {
			body = RequestBody.create(CONTENT_TYPE_JSON,JSON.toJSONString(bodyParams));
		}
		return body;
	}
	
	/**
	 * 
	 * @Description url参数连接符
	 * @param url
	 * @return
	 */
	private String paramsJoint(StringBuilder url) {
		return url.indexOf("?") > -1 ? "&" : "?";
	}
	
	/**
	 * 
	 * @Description object 转 str
	 * @param value
	 * @return
	 */
	private String objectToString(Object value) {
		return value == null || (value instanceof String) ? (String) value : JSON.toJSONString(value);
	}
	
}

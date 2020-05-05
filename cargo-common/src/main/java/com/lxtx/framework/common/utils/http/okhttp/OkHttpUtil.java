package com.lxtx.framework.common.utils.http.okhttp;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.utils.http.method.RequestTypeEnum;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.Request.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
			.writeTimeout(60, TimeUnit.SECONDS)
			.connectionPool(new ConnectionPool(64,5,TimeUnit.MINUTES))
            .build();
    private static OkHttpTool okHttpTool;
    
    /**
	 * @Description 初始化OkHttpTool
	 * @return
	 */
	private static OkHttpTool getOkHttpTool() {
		if (okHttpTool != null) {
			return okHttpTool;
		}
		synchronized (OkHttpTool.class) {
			if (okHttpTool == null) {
				log.info("init OkHttpTool");
				okHttpTool = new OkHttpTool(60 * 1000, 60 * 1000, false);
			}
		}
		return okHttpTool;
	}

    public static String get(String url, Map<String, Object> headers) throws Exception{

        logger.info("okhttputil get request, url:[{}], headers:[{}]", url, headers);
        Builder requestBuilder = new Request.Builder();
        if(!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, Object> header : headers.entrySet()) {
                requestBuilder.addHeader(header.getKey(), header.getValue().toString());
            }
        }

        Request request = requestBuilder
                .get()
                .url(url)
                .build();

        Response response = null;
        String respBody = null;
        try {
            response = client.newCall(request).execute();
            respBody = response.body().string();
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            if(response != null) {
                response.close();
            }
            throw e;
        }

        logger.info("okhttputil get response, result:[{}]", respBody);
        return respBody;
    }

    public static String get(String url) throws Exception{
        return get(url, null);
    }

	@SuppressWarnings("deprecation")
	public static String post(String url, String bodyParams, Map<String, Object> headers) throws Exception {
        logger.info("okhttputil post request, url:[{}], bodyParams:[{}], headers:[{}]", url, bodyParams, headers);

        //表单请求参数
        FormBody.Builder bodyBuilder = new FormBody.Builder();

//        if(!CollectionUtils.isEmpty(bodyParams)) {
//            for (Map.Entry<String, Object> param : bodyParams.entrySet()) {
//                bodyBuilder.add(param.getKey(), param.getValue().toString());
//            }
//        }

        RequestBody body = bodyBuilder.build();

        //设置body请求参数
        if (bodyParams != null) {
        	 body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyParams);
        }

        //设置请求头部信息
        Builder requestBuilder = new Request.Builder();
        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, Object> header : headers.entrySet()) {
                requestBuilder.addHeader(header.getKey(), header.getValue().toString());
            }
        }

        //封装request请求对象
        Request request = requestBuilder
                .post(body)
                .url(url)
                .build();

        //发送post请求
        Response response = null;
        String respBody = null;
        try {
            response = client.newCall(request).execute();
            respBody = response.body().string();
        } catch (Exception e) {
            if (response != null) {
                response.close();
            }
            throw e;
        } finally {
            logger.info("okhttputil post response, result:[{}]", JSONObject.toJSONString(respBody));
        }
        return respBody;
    }

    public static String post(String url, String bodyParams) throws Exception{
        return post(url, bodyParams, null);
    }
    
	/**
	 * 
	 * @Description get类型
	 * @param url
	 * @param headerParams
	 * @param queryParams
	 * @return
	 * @throws Exception 
	 */
	public static String get(String url, Object headerParams, Object queryParams) throws Exception {
		return getOkHttpTool().common(url, headerParams, queryParams, null, RequestTypeEnum.GET, null);
	}

	/**
	 * 
	 * @Description post类型
	 * @param url
	 * @param headerParams
	 * @param queryParams
	 * @param bodyParams
	 * @return
	 * @throws Exception 
	 */
	public static String post(String url, Object headerParams, Object queryParams, Object bodyParams, MediaType mediaType) throws Exception {
		return getOkHttpTool().common(url, headerParams, queryParams, bodyParams, RequestTypeEnum.POST, mediaType);
	}

	/**
	 * 
	 * @Description put类型
	 * @param url
	 * @param headerParams
	 * @param queryParams
	 * @param bodyParams
	 * @return
	 * @throws Exception 
	 */
	public static String put(String url, Object headerParams, Object queryParams, Object bodyParams, MediaType mediaType) throws Exception {
		return getOkHttpTool().common(url, headerParams, queryParams, bodyParams, RequestTypeEnum.PUT, mediaType);
	}
	
	/**
	 * 
	 * @Description delete类型
	 * @param url
	 * @param headerParams
	 * @param queryParams
	 * @param bodyParams
	 * @return
	 * @throws Exception 
	 */
	public static String delete(String url, Object headerParams, Object queryParams, Object bodyParams, MediaType mediaType) throws Exception {
		return getOkHttpTool().common(url, headerParams, queryParams, bodyParams, RequestTypeEnum.DELETE, mediaType);
	}

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        params.put("test", "1");
        //TODO 测试该create方法
        RequestBody body = RequestBody.create(JSONObject.toJSONString(params), MediaType.parse("application/json; charset=utf-8"));
        System.out.println(11);
        System.out.println(body);
    }
}

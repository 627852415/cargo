package com.lxtx.framework.common.log.interceptor;

import com.alibaba.fastjson.JSON;
import com.lxtx.framework.common.utils.IPAddressUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志拦截器 由于HttpServletRequest的inputstream只能读一次，LoggerInterceptor 必须读取
 * RepeatedReadRequestWrapper的inputstream。
 *
 * @author zkj
 * @date 2018/8/8
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    /**
     * 处理日志拦截器不打印body的url
     */
    private String ignoreBodyUrl = PropertiesUtil.getString(PropertiesUtil.IGNORE_BODY_URL);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        setRequestLog(request);
        return super.preHandle(request, response, handler);
    }

    /**
     * 设置请求日志
     *
     * @param requestInput
     * @throws IOException
     */
    private void setRequestLog(HttpServletRequest requestInput) throws IOException {
        HttpServletRequest request = requestInput;
        StringBuilder sb = new StringBuilder();
        String uri = request.getRequestURI();

        if (null != uri) {
            sb.append("【请求URI】").append(uri);
        }
        String ip = IPAddressUtil.getIpAddress(request);
        sb.append("【IP信息】").append(ip);
        sb.append("【请求客户端】").append(request.getHeader(HttpHeaders.USER_AGENT));

        String traceId = null;

        /*
         * 由于HttpServletRequest的inputstream只能读一次，LoggerInterceptor 必须读取
         * RepeatedReadRequestWrapper的inputstream。
         */

        // ------------- 统一控制台打印日志，若有不同需对request进行判断再处理 -----------------
        String queryString = request.getQueryString();
        if (null != queryString) {
            sb.append("?").append(URLDecoder.decode(queryString, StandardCharsets.UTF_8.name()));
        }
        try {
            String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            if (StringUtils.isBlank(body)) {
                Map<String, String[]> parameterMap = request.getParameterMap();
                if(!CollectionUtils.isEmpty(parameterMap)){
                    HashMap<String, String > hashMap = new HashMap<>(parameterMap.size());
                    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                        hashMap.put(entry.getKey(),entry.getValue()[0]);
                    }

                    body = JSON.toJSONString(hashMap);
                }

            }
            //处理日志拦截器不打印body的url
            if (!(StringUtils.isNotBlank(uri) && StringUtils.isNotBlank(ignoreBodyUrl) && ignoreBodyUrl.contains(uri))) {
                sb.append("【请求参数】").append(body);
            }
            traceId = LoggerContext.getTraceId(body);
            LoggerContext.setStartTimeTL(System.currentTimeMillis());
        } catch (IOException e1) {
            logger.error(e1.getMessage(), e1);
            throw e1;
        } finally {
            logger.info("********【请求id】{}{}", traceId, sb);
        }
        // ------------- 统一控制台打印日志，若有不同需对request进行判断再处理 -----------------

    }
}

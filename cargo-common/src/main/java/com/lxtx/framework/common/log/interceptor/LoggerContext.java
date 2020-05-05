package com.lxtx.framework.common.log.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.utils.UUIDTools;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 保存http请求日志
 * 
 * @author zkj
 * @date 2018/8/8
 */
public class LoggerContext {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerContext.class);

	private static final ThreadLocal<String> requestLogTL = new ThreadLocal<>();

	private static final ThreadLocal<String> requestIdTL = new ThreadLocal<>();

	private static final ThreadLocal<Long> startTimeTL = new ThreadLocal<>();

	private LoggerContext() {
	}

	public static String getTraceId(String body) {
		if(StringUtils.isBlank(body)){
			return null;
		}
		String traceId = null;
		try {
			JSONObject bodyObject = JSONObject.parseObject(body);
			String traceObject = bodyObject.getString(Constants.SIGNATURE_TRACE_ID_KEY);

			if (StringUtils.isBlank(traceObject)) {
				traceId = Constants.SYS.concat(UUIDTools.getUUID());
				requestIdTL.set(traceId);
				return traceId;
			} else {
				requestIdTL.set(traceObject);
				return traceObject;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.info("参数解析异常 body：" + body);
			String sub = "\"" + Constants.SIGNATURE_TRACE_ID_KEY + "\":\"";
			if (body.contains(sub)) {
				traceId = body.substring(body.indexOf(sub) + 11);
				traceId = traceId.substring(0, traceId.indexOf("\""));
				requestIdTL.set(traceId);
			}
			return traceId;
		}
	}

	public static String getRequestId() {
		return requestIdTL.get();
	}

	public static Long getStartTime() {
		return startTimeTL.get();
	}

	public static void setStartTimeTL(Long startTime) {
		startTimeTL.set(startTime);
	}

	public static String getRuquestLog() {
		return requestLogTL.get();
	}

	public static void setRequestLog(String requestLog) {
		requestLogTL.set(requestLog);
	}

	public static void main(String[] args) {
		String ss ="{\"timestamp\":\"1542012808840\",\"signature\":\"1BB813504452E595EFA671DB592E\",\"signKey\":\"GiI96RYjGulSnDa3dlrNLAOK8Z6YE4\",\"traceId\":\"ca3de42c62844efb9375e989092ca075\",\"contents\":[\"221883709035515904\"]}";

		System.out.println(JSONObject.parseObject(ss));
	}
}
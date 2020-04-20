package com.lxtx.im.admin.web.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.log.filter.RepeatedReadRequestWrapper;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhiHua chen
 * @Created data on 2018/08/07
 */
@Slf4j
public class CheckSignatureInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Options
        if (Constants.HTTP_OPTIONS.equals(request.getMethod())) {
            return true;
        }
		Map<String, String> params = getRequestParameter(request);

		boolean checkSignatureSwitch = PropertiesUtil.getBoolean(PropertiesUtil.SIGNATURE_SERVER_SWITCH);
		// 读取配置是否开启签名校验
		/*if (!checkSignatureSwitch) {
			return SignUtils.checkSign(params, Constants.SIGNATURE_EXPIRE_TIME);
		}*/
		return true;
		//return super.preHandle(request, response, handler);
	}

	/**
	 * 获取请求参数
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getRequestParameter(HttpServletRequest request) throws IOException {

		String bodyString = null;
		Map<String, String> params;

		if (request instanceof RepeatedReadRequestWrapper) {
			bodyString =  StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
		}

		if (StringUtils.isBlank(bodyString)) {
			Enumeration<String> paramNames = request.getParameterNames();
			params = new TreeMap<>();
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement();
				String value = request.getParameter(name);
				params.put(name, value);
			}
		} else {
			params = JSONObject.parseObject(bodyString, new TypeReference<Map<String, String>>() {
			},Feature.OrderedField);
		}
		return params;
	}


}

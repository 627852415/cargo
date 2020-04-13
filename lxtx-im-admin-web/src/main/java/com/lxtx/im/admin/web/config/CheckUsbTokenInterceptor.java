package com.lxtx.im.admin.web.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.framework.common.log.filter.RepeatedReadRequestWrapper;
import com.lxtx.framework.common.utils.IPAddressUtil;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.WebUsbTokenService;
import com.lxtx.im.admin.service.request.UsbTokenReq;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * usbToken拦截器
 *
 * @author CaiRH
 * @date 20190718
 */
@Slf4j
public class CheckUsbTokenInterceptor extends HandlerInterceptorAdapter {

    @Value("${app.ca}")
    private String ca;
    @Lazy
    @Autowired
    private DictService dictService;
    @Lazy
    @Autowired
    private WebUsbTokenService webUsbTokenService;

    private static final String CERT = "cert";
    private static final String SIGN = "sign";
    private static final String TIME = "time";
    private static final String TOKEN = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Options
        if (Constants.HTTP_OPTIONS.equals(request.getMethod())) {
            return true;
        }
        Map<String, String> params = getRequestParameter(request);

        Boolean isOpenUsbMode = Boolean.valueOf(dictService.getDictValue(DictConstants.DICT_DOMAIN_GLOBAL, DictConstants.DICT_KEY_USB_TOKEN_SWITCH));
        if (isOpenUsbMode) {
            UsbTokenReq usbTokenReq = new UsbTokenReq();
            usbTokenReq.setCa(ca);
            usbTokenReq.setCert(params.get(CERT));
            usbTokenReq.setSign(params.get(SIGN));
            usbTokenReq.setTime(params.get(TIME));
            usbTokenReq.setToken(params.get(TOKEN));
            usbTokenReq.setIp(IPAddressUtil.getIpAddress(request));

            String userName;
            try {
                userName = ShiroUtils.getUserName();
            } catch (Exception e) {
                log.info("ShiroUtils获取不到用户，信息，将尝试从参数获取username：{}", params);
                userName = params.get("username");
            }
            usbTokenReq.setUserName(userName);

            return (boolean) webUsbTokenService.verifyToken(usbTokenReq).getData();
        }

        return super.preHandle(request, response, handler);
    }

    /**
     * 获取请求参数global.exception.service.error.default.msg
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Map<String, String> getRequestParameter(HttpServletRequest request) throws IOException {
        String bodyString = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        Map<String, String> params;

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
            }, Feature.OrderedField);
        }

        String cert = request.getHeader(CERT);
        if (StringUtils.isNotBlank(cert)) {
            params.put(CERT, cert);
        }
        String sign = request.getHeader(SIGN);
        if (StringUtils.isNotBlank(sign)) {
            params.put(SIGN, sign);
        }
        String time = request.getHeader(TIME);
        if (StringUtils.isNotBlank(time)) {
            params.put(TIME, time);
        }
        String token = request.getHeader(TOKEN);
         if (StringUtils.isNotBlank(token)) {
            params.put(TOKEN, token);
        }
        return params;
    }


}

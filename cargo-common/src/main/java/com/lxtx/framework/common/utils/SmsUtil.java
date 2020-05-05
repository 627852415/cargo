package com.lxtx.framework.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.utils.http.method.Get;
import com.lxtx.framework.common.utils.http.method.Post;
import com.lxtx.framework.common.utils.http.soa.HttpInvoker;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.StringEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  国际、国内短信 发送工具类
 * </p>
 *
 * @author liboyan
 * @version 1.0
 * @create 2018-10-23
 */
@Getter
@Setter
@Slf4j
public class SmsUtil {

    private final String ENCODE_UTF_8 = "UTF-8";

    private final String AC_SEND = "send";

    /**
     * 国外短信发送渠道url
     */
    private String url;

    /**
     * 国外短信账户密码
     */
    private String password;

    /**
     * 国外短信账户
     */
    private String account;

    /**
     * 国内短信发送渠道url
     */
    private String baseurl;

    /**
     * 国内短信账户
     */
    private String username;

    /**
     * 国内短信账户密码
     */
    private String passeordIsm;

    /**
     * 功能描述: 发送手机短信
     *
     * @param countryCode 国际区号
     * @param mobile      手机号
     * @param msg         短信内容
     * @return
     * @author liboyan
     * @date 2018-10-23 17:27
     */
    public String senSms(String countryCode, String mobile, String msg) {

        if (Constants.CHINA_PHONE_CODE.equals(countryCode)) {
            return sendPhoneMessageCN(mobile, msg,"435974");
        } else {
            mobile = PhoneUtils.toSMSGateway(countryCode, mobile);
            return sendPhoneMessageSG(mobile, msg);
        }
    }

    /**
     * 功能描述: 发送国际短信
     *
     * @param mobile
     * @param msg
     * @return
     * @author liboyan
     * @date 2018-10-23 17:24
     */
    public String sendPhoneMessageSG(String mobile, String msg) {
        String result = "";
        try {
            JSONObject map = new JSONObject();
            map.put("account", account);
            map.put("password", password);
            map.put("msg", msg);
            map.put("mobile", mobile);
            String params = map.toJSONString();
            Post post = new Post(url);
            StringEntity stringEntity = new StringEntity(params, ENCODE_UTF_8);
            post.setEntity(stringEntity);

            //发送json数据需要设置contentType
            stringEntity.setContentType("application/json");
            result = HttpInvoker.execute(post).getBody();
            log.debug("[sendPhoneMessageSG]>>>> 返回参数为:" + result + "发送的手机号码为:" + mobile);
        } catch (Exception e) {
            log.error("[sendPhoneMessageSG] {},{}", e.getMessage(), e);
        }
        return result;
    }

    /**
     * 功能描述:  发送国内短信
     *
     * @param mobile
     * @param msg
     * @return
     * @author liboyan
     * @date 2018-10-23 17:25
     */
    public String sendPhoneMessageCN(String mobile, String msg,String template) {
        String result = "";
        try {
            Map map = new HashMap(6);
            map.put("uid", username);
            map.put("pwd", passeordIsm);
            map.put("ac", AC_SEND);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", msg);
            map.put("content", jsonObject.toJSONString());
            map.put("mobile", mobile);
            map.put("template", template);

            Get get = new Get(baseurl);
            get.setParams(map);

            result = HttpInvoker.execute(get).getBody();
            log.debug("[sendPhoneMessageCN]>>>> 返回参数为:" + result + "发送的手机号码为:" + mobile);
        } catch (Exception e) {
            log.error("[sendPhoneMessageCN] {},{}", e.getMessage(), e);
        }
        return result;
    }

    public static void main(String[] args) {
        SmsUtil smsUtil = new SmsUtil();
        // 国际短信
        smsUtil.setUrl("http://intapi.253.com/send/json");
        smsUtil.setAccount("I7630452");
        smsUtil.setPassword("rvuZ0sb3nMab70");
        //smsUtil.sendPhoneMessageSG("8618217003986", "1234567");

        // 国内短信
        smsUtil.setBaseurl("http://api.sms.cn/sms/");
        smsUtil.setUsername("barbie1101");
        smsUtil.setPasseordIsm("7f1747ef8bb74057a840e94939c17231");
        smsUtil.sendPhoneMessageCN("18217003986", "你好测试","435974");
    }


}

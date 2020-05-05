package com.lxtx.framework.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.constants.PropertiesContants;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin hj
 * @title: JsonRpcUtil
 * @date 2019/6/215:24
 */
@Slf4j
public class JsonRpcUtil {



    private static final String rpcSerUrl = PropertiesUtil.getString(PropertiesContants.JSON_SIX_RPC_URL);;
    private static final String sixPrivateKey = PropertiesUtil.getString(PropertiesContants.JSON_SIX_RPC_PRIVATE_KEY);
    private static final String yebPrivateKey = PropertiesUtil.getString(PropertiesContants.JSON_YEB_RPC_PRIVATE_KEY);

    /**
     * 这个body就是之前要校验的参数 跟之前校验一样
     * @param body
     * @return 这个接口返回的signature 就是你们要传给资金托管的秘钥
     */
    public static String createSign(String body) {
        RestTemplate restTemplate = new RestTemplate();
        String data = body;
        Map<String, Object> map = new HashMap<>();
        map.put("jsonrpc", "2.0");
        map.put("method", "bcb_genericSign");
        map.put("id", "1");
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("coinType", "0x1001");
        paraMap.put("priKey", yebPrivateKey);
        paraMap.put("data", data);
        map.put("params", paraMap);
        log.info("MimaKey=",yebPrivateKey);
        String string = restTemplate.postForEntity(rpcSerUrl, JSON.toJSONString(map), String.class).getBody();
        if (!StringUtils.isEmpty(string)) {
            JSONObject object = JSONObject.parseObject(string);
            JSONObject results = object.getJSONObject("result");
            log.info("生成签名返回内容,[{}]",JSON.toJSONString(string));
            String sig = results.getString("signature");
            return sig;
        }
        return null;
    }

    public static String create6xSign(String body) {
        RestTemplate restTemplate = new RestTemplate();
        String data = strTo16(body);

        Map<String, Object> map = new HashMap<>();
        map.put("jsonrpc", "2.0");
        map.put("method", "bcb_genericSign");
        map.put("id", "1");
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("coinType", "0x1001");
        paraMap.put("priKey", sixPrivateKey);
        paraMap.put("data", data);
        map.put("params", paraMap);

        log.info("请求签名所传参数,[{}]",JSON.toJSONString(map));
        String string = restTemplate.postForEntity(rpcSerUrl, JSON.toJSONString(map), String.class).getBody();
        if (!StringUtils.isEmpty(string)) {
            JSONObject object = JSONObject.parseObject(string);
            JSONObject results = object.getJSONObject("result");
            log.info("生成签名返回内容,[{}]",JSON.toJSONString(string));
            String sig = results.getString("signature");
            return sig;
        }
        return null;
    }


    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str.toUpperCase();
    }


}

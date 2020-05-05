package com.lxtx.framework.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.utils.http.method.Post;
import com.lxtx.framework.common.utils.http.soa.HttpInvoker;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Component;

/**
 * @description: 电报推送工具类
 * @author: CXM
 * @create: 2018-10-23 15:52
 **/
@Slf4j
@Component
public class TelegramUtil {
    /**
     * 电报url
     */
    private static final String URL = "https://api.telegram.org/bot";

    /**
     * 推送消息到电报
     *
     * @param token 机器人token
     * @param chatId 群id
     * @param text 推送内容
     * @return
     */
    public String sendTelegram(String token, String chatId, String text){
        String postUrl = URL + token + "/sendMessage";
        JSONObject map = new JSONObject();
        map.put("chat_id", chatId);
        map.put("text", text);

        String params = map.toString();
        Post post = new Post(postUrl);
        StringEntity s = new StringEntity(params, "UTF-8");
        //发送json数据需要设置contentType
        s.setContentType("application/json");
        post.setEntity(s);
        String result = HttpInvoker.execute(post).getBody();
        log.info("[推送到电报]>>>> 返回参数为:" + result );
        return result;
    }
}

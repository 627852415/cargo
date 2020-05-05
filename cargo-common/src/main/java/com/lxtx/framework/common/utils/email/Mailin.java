package com.lxtx.framework.common.utils.email;


import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
/**
* @description:  邮件发送
* @author:   CXM
* @create:   2018-10-26 17:15
*/
public class Mailin {
    /**
     * 代理转发的网站
     */
    private String baseUrl;
    /**
     * 代理发邮件网站api的key
     */
    private String apiKey;

    public Mailin(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public String send_email(Object data) {
        String json = JSON.toJSONString(data);
        return post("email", json);
    }

    public String post(String resource, String input) {
        try {
            return do_request(resource, "POST", input);
        } catch (Exception e) {

        }
        return null;
    }

    public String do_request(String resource, String method, String input) throws Exception {
        String url = baseUrl + "/" + resource;
        String key = apiKey;
        String content_header = "application/json";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestProperty("api-key", key);
        con.setRequestProperty("Content-Type", content_header);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod(method);
        con.setUseCaches(false);

        if (input != "" && method != "GET") {
            DataOutputStream outStream = new DataOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream, "UTF-8"));
            writer.write(input);
            writer.flush();
            writer.close();
            outStream.close();
        }

        int responseCode = con.getResponseCode();
        String inputLine;
        StringBuffer response = new StringBuffer();
        BufferedReader in;

        if (200 <= responseCode && responseCode < 300) {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}

package com.lxtx.framework.common.utils.bcbbank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.constants.PropertiesContants;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.framework.common.utils.http.method.Post;
import com.lxtx.framework.common.utils.http.soa.HttpInvoker;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lin hj
 * @date 2019/4/23
 */
@Slf4j
public class BaseBcbBankClient {


    /**
     *
     * @param key
     * @param value
     * @param type false表示value 不加引号
     * @param isEnd true 表示拼接结尾，不加逗号
     * @param stringBuilder
     * @return
     */
    protected  StringBuilder appendString(String key,String value,boolean type, boolean isEnd ,StringBuilder stringBuilder){
        String end = "";
        if(!isEnd){
            end = ",";
        }

        if(type){
            stringBuilder.append("\""+key+"\":\""+value+ "\""+end);
        }
        else{
            stringBuilder.append("\""+key+"\":"+value+ end);
        }
        return stringBuilder;
    }

    protected  StringBuilder appendString(String key,String value,boolean type, StringBuilder stringBuilder){
        return appendString(key, value, type, false,stringBuilder);
    }

    protected  StringBuilder appendString(String key,String value, StringBuilder stringBuilder){
        return appendString(key, value, true, false,stringBuilder);
    }

    protected  StringBuilder appendEndString(String key,String value,StringBuilder stringBuilder){
        return appendString(key, value, true, true,stringBuilder);
    }

    protected  StringBuilder appendInt(String key,String value, StringBuilder stringBuilder){
        return appendString(key, value, false, false,stringBuilder);
    }

    protected  StringBuilder appendEndInt(String key,String value,StringBuilder stringBuilder){
        return appendString(key, value, false, true,stringBuilder);
    }

    public static void main(String[] args) {
        StringBuilder body = new StringBuilder();
        BaseBcbBankClient client = new BaseBcbBankClient();
        client.appendString("Application_ID","123",body);
        client.appendInt("Application_ID2","1234",body);
        client.appendEndString("Application_ID3","123",body);
        client.appendEndInt("Application_ID24","1234",body);
        System.out.println(body.toString());
    }

    protected StringBuilder builderBodyPre() {
        StringBuilder body = new StringBuilder();
        body.append("{");
        return body;
    }

    protected StringBuilder builderBodyEnd(StringBuilder stringBuilder) {
        stringBuilder.append("}");
        return stringBuilder;
    }


    public String  post(String url,StringBuilder body) throws Exception{
        String result = null;
        String realUrl = getHttp() + getHost() + url;
        String params = body.toString();
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Authorization", getToken());
            HttpEntity<String> formEntity = new HttpEntity<String>(params, headers);
            result = restTemplate.postForObject(realUrl, formEntity, String.class);
            log.info("请求BCB银行接口，URL：[{}]，请求参数：[{}]，返回结果信息：[{}]",
                    realUrl, params, JSONObject.toJSONString(result));
            // 校验数据格式
            JSONObject.parseObject(result);
        }
        catch (JSONException ex) {
            try {
                JSONObject.parseArray(result);
            } catch (JSONException ex1) {
                throw new Exception("返回JSON格式出错：" + result);
            }
        } catch (Exception e) {
            throw new Exception("网络不通,http请求失败!");
        }
        return result;
    }

    protected String executeHttp(String url, StringBuilder body) throws Exception {
        //发送post请求获取数据
        String realUrl = getHttp() + getHost()  +url;
        String result = null;
        try {
            String str = body.toString();
            StringEntity stringEntity = new StringEntity(str);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            Post post = new Post(realUrl);
            post.setEntity(stringEntity);
            post.setSocketTimeout(99999);
            //post.setConnectTimeout(999999999);
            post.setHeader("Authorization",getToken());
            result = HttpInvoker.execute(post).getBody();

            log.info("请求BCB银行接口，URL：[{}]，请求参数：[{}]，返回结果信息：[{}]",
                    url, body.toString(), JSONObject.toJSONString(result));

            // 校验数据格式
            JSONObject.parseObject(result);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(result);
            } catch (JSONException ex1) {
                throw new Exception("返回JSON格式出错：" + result);
            }
        } catch (Exception e) {
            throw new Exception("网络不通,http请求失败!");
        }
        return result;
    }

    protected <T> T transferToResultDto(String result, TypeReference<T> typeReference) {
        T t = JSON.parseObject(result, typeReference);
//        ResultDTO dto = (ResultDTO) t;
//
//        if (!dto.getFlag()) {
//            throw new LxtxException(dto.getCodeEnum(), dto.getMessage());
//        }

        return t;
    }

    protected String getHost(){
        return  PropertiesUtil.getString(PropertiesContants.BCB_BANK_CLIENT_HOST);
    }
    protected String getHttp(){
        return  PropertiesUtil.getString(PropertiesContants.BCB_BANK_CLIENT_HTTP);
    }

    protected String getAppId(){
        return  PropertiesUtil.getString(PropertiesContants.BCB_BANK_CLIENT_APPID);
    }

    protected String getToken(){
        return  PropertiesUtil.getString(PropertiesContants.BCB_BANK_CLIENT_TOKEN);
    }

    protected String getHeapCardNo(){
        return  PropertiesUtil.getString(PropertiesContants.BCB_BANK_CLIENT_HEAP_CARD_NO);
    }


    protected String getHeapAccountId(){
        return  PropertiesUtil.getString(PropertiesContants.BCB_BANK_CLIENT_HEAO_ACCOUNT_ID);
    }


    protected String getHeapUserId(){
        return  PropertiesUtil.getString(PropertiesContants.BCB_BANK_CLIENT_HEAP_USERID);
    }




}

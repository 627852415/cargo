package com.lxtx.framework.common.utils.yeb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.beust.jcommander.internal.Maps;
import com.lxtx.framework.common.constants.PropertiesContants;
import com.lxtx.framework.common.utils.JsonRpcUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.framework.common.utils.http.method.Get;
import com.lxtx.framework.common.utils.http.method.Post;
import com.lxtx.framework.common.utils.http.okhttp.OkHttpUtil;
import com.lxtx.framework.common.utils.http.soa.HttpInvoker;
import com.lxtx.framework.common.utils.yeb.model.YebResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;

import java.util.Map;

/**
 * author lin hj on 2019/3/29
 */
@Slf4j
public class BaseYebClient {


    public BaseYebClient setSetSingHeader(boolean setSingHeader) {
        this.setSingHeader = setSingHeader;
        return this;
    }

    public boolean isSetSingHeader() {
        return setSingHeader;
    }

    private boolean setSingHeader ;

    public YebResultDTO execute(String url,StringBuilder params) throws Exception {
        String  result = executeHttp(url,params);
        YebResultDTO<String> resultDto = transferToResultDto(result,
                new TypeReference<YebResultDTO<String>>() {
                });
        return resultDto;
    }


    public YebResultDTO executeSimple(String url,StringBuilder params) throws Exception {
        String  result = executeSimpleHttp(url,params);
        YebResultDTO<String> resultDto = transferToResultDto(result,
                new TypeReference<YebResultDTO<String>>() {
                });
        return resultDto;
    }


    protected String executeSimpleHttp(String signUrl, StringBuilder body) throws Exception {
        return executeSimpleHttp(signUrl,body,false);
    }

    protected String executeSimpleHttp(String signUrl, StringBuilder body,boolean isGet) throws Exception {

        //发送post请求获取数据
        String realUrl = getHttp() + getHost()  +signUrl+body;
        String result = null;
        try {
            StringEntity s = new StringEntity(body.toString());
            s.setContentEncoding("UTF-8");
            if(!isGet){
                result = OkHttpUtil.post(realUrl,null);
            }else{
                result = OkHttpUtil.get(realUrl);
            }

            log.info("请求余额宝接口，URL：[{}]，请求参数：[{}]，返回结果信息：[{}]",
                    signUrl, body.toString(), JSONObject.toJSONString(result));
            // 校验数据格式
            JSONObject.parseObject(result);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(result);
            } catch (JSONException ex1) {
                log.error("余额宝返回JSON格式出错!url=[{}],response=[{}],异常信息msg=[{}]",
                        signUrl,result,ex1.getMessage(),ex1);
                throw new Exception("返回JSON格式出错："+result);
            }
        } catch (Exception e) {
            log.error("余额宝网络不通,http请求失败!url=[{}],response=[{}],异常信息msg=[{}]",
                    signUrl,result,e.getMessage(),e);
            throw new Exception("余额宝网络不通,http请求失败!");
        }
        return result;
    }

    /**
     * 计算签名和发送HTTP请求
     *
     * @param signUrl
     * @param body
     * @return
     */
    protected String executeHttp(String signUrl, StringBuilder body) throws Exception {

      return executeHttp(signUrl,body,false);
    }
    protected String executeHttpGet(String signUrl, StringBuilder body) throws Exception {
        //发送post请求获取数据
        String realUrl = getHttp() + getHost()  + signUrl  + body.toString() ;

        String result = null;

        try {
            result = OkHttpUtil.get(realUrl);
            log.info("请求余额宝接口，URL：[{}]，请求参数：[{}]，返回结果信息：[{}]",
                    signUrl, body.toString(), JSONObject.toJSONString(result));

            // 校验数据格式
            JSONObject.parseObject(result);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(result);
            } catch (JSONException ex1) {
                log.error("余额宝返回JSON格式出错!url=[{}],response=[{}],异常信息msg=[{}]",
                        signUrl,result,ex1.getMessage(),ex1);
                throw new Exception("返回JSON格式出错："+result);
            }
        } catch (Exception e) {
            log.error("余额宝网络不通,http请求失败!url=[{}],response=[{}],异常信息msg=[{}]",
                    signUrl,result,e.getMessage(),e);
            throw new Exception("余额宝网络不通,http请求失败!");
        }
        return result;
    }


    protected String executeHttpAdd(String signUrl, StringBuilder body,Boolean isGet,String signSimple) throws Exception {

        //计算签名
        String signature = EncryptUtil.hmacSha256(signSimple, getSecretKey());

        //发送post请求获取数据
        String realUrl = getHttp() + getHost()  +signUrl+ "?sign=" + signature+body;
        String result = null;
        String header = null;
        try {
            StringEntity s = new StringEntity(body.toString());
            s.setContentEncoding("UTF-8");
            if(setSingHeader){
                header =  JsonRpcUtil.createSign(signature);
            }
            if(!isGet){
                Post post = new Post(realUrl);
                post.setEntity(s);
                result = HttpInvoker.execute(post).getBody();
            }else{
                Get post = new Get(realUrl);
                post.setEntity(s);
                result = HttpInvoker.execute(post).getBody();
            }


            log.info("请求余额宝接口，URL：[{}]，请求参数：[{}]，返回结果信息：[{}]",
                    signUrl, body.toString(), JSONObject.toJSONString(result));

            // 校验数据格式
            JSONObject.parseObject(result);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(result);
            } catch (JSONException ex1) {
                log.error("余额宝返回JSON格式出错!url=[{}],response=[{}],异常信息msg=[{}]",
                        signUrl,result,ex1.getMessage(),ex1);
                throw new Exception("返回JSON格式出错："+result);
            }
        } catch (Exception e) {
            log.error("余额宝网络不通,http请求失败!url=[{}],response=[{}],异常信息msg=[{}]",
                    signUrl,result,e.getMessage(),e);
            throw new Exception("余额宝网络不通,http请求失败!");
        }
        return result;
    }

    protected String executeHttp(String signUrl, StringBuilder body,Boolean isGet) throws Exception {

        return executeHttp(signUrl,body,isGet,null);

    }

    protected String executeHttp(String signUrl, StringBuilder body,Boolean isGet,String signInfo) throws Exception {

        //拼接参数
        if(StringUtils.isEmpty(signInfo)){
            signInfo = appendParams(body);
        }
        //计算签名
        String signature = EncryptUtil.hmacSha256(signInfo, getSecretKey());

        //发送post请求获取数据
        String realUrl = getHttp() + getHost()  +signUrl+ "?sign=" + signature+body;
        String result = null;
        String header = null;
        try {
            if(setSingHeader){
                header =  JsonRpcUtil.createSign(signature);
                setSingHeader = false;
            }
            Map<String,Object> headerMap = Maps.newHashMap();
            headerMap.put("dsign",header);
            if(!isGet){
                result = OkHttpUtil.post(realUrl,null,headerMap);
            }else{
                log.info("请求余额宝接口get,[{}]",header);
                result = OkHttpUtil.get(realUrl,headerMap);
            }
            log.info("请求余额宝接口，URL：[{}]，请求参数：[{}]，hearder:[dsign：{}],返回结果信息：[{}]",
                    signUrl, body.toString(), header,JSONObject.toJSONString(result));

            // 校验数据格式
            JSONObject.parseObject(result);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(result);
            } catch (JSONException ex1) {
                log.error("余额宝返回JSON格式出错!url=[{}],response=[{}],异常信息msg=[{}]",
                        signUrl,result,ex1.getMessage(),ex1);
                throw new Exception("返回JSON格式出错："+result);
            }
        } catch (Exception e) {
            log.error("余额宝网络不通,http请求失败!url=[{}],response=[{}],异常信息msg=[{}]",
                    signUrl,result,e.getMessage(),e);
            throw new Exception("余额宝网络不通,http请求失败!");
        }
        return result;
    }

    private String appendParams(StringBuilder body) {
        String signInfo = "";
        String signInfos[] = body.toString().split("&");
        for(String sign:signInfos){
            if(StringUtils.isEmpty(sign))
                continue;
            sign = sign.substring(sign.indexOf("=")+1);
            signInfo+=sign;
        }
        signInfo+=getApiKey();
        return signInfo;
    }

    public  <T> T transferToResultDto(String result, TypeReference<T> typeReference) {
        T t = JSON.parseObject(result, typeReference);

        YebResultDTO dto = (YebResultDTO) t;

        if (dto.getCode()!=0) {
           // throw new LxtxException(dto.getCode()+"", dto.getMessage());
        }

        return t;
    }


    private String getHost(){
        return  PropertiesUtil.getString(PropertiesContants.YEB_CLIENT_HOST);
    }
    private String getHttp(){
        return  PropertiesUtil.getString(PropertiesContants.YEB_CLIENT_HTTPS);
    }

    protected String getApiKey(){
        return  PropertiesUtil.getString(PropertiesContants.YEB_CLIENT_API_KEY);
    }

    public String getSecretKey(){
        return  PropertiesUtil.getString(PropertiesContants.YEB_CLIENT_SECRET_KEY);
    }


    public String getMchId(){
        return  PropertiesUtil.getString(PropertiesContants.YEB_CLIENT_MCHID);
    }


}

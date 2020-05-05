package com.lxtx.framework.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.utils.http.okhttp.OkHttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 根据经纬度获取地址
 * @author 唐大用
 * @date 2019-01-08
 */

public class BaiDuAddressUtil {

    private static final String MAP_URL = "http://api.map.baidu.com/geocoder/v2/";

    public static String getCountryCode(String latitude, String longitude, String ak){
        JSONObject addressObj  = null;
        try {
            String addressByPoint = getAddressByPoint(latitude, longitude, ak);
            if(StringUtils.isBlank(addressByPoint)){
                return null;
            }
            JSONObject object = JSONObject.parseObject(addressByPoint);
            JSONObject resultObj = object.getJSONObject("result");
            addressObj = resultObj.getJSONObject("addressComponent");
        } catch (Exception e) {
            return null;
        }
        return addressObj.getString("country_code_iso2");
    }

    public static String getAddressByPoint(String latitude, String longitude, String ak) throws Exception {
        /** location的格式例如：39.983424,20116.322987 */
        StringBuilder builder = new StringBuilder();
        builder.append(MAP_URL);
        builder.append("?ak=").append(ak);
        builder.append("&output=json");
        builder.append("&location=");
        builder.append(latitude);
        builder.append(",");
        builder.append(longitude);

        return OkHttpUtil.get(builder.toString());
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(getCountryCode("45.7675229621", "99.9536132813", "Ya2nSaqjT3vNrIgba1v4nfWzSxGdtgZD"));
        System.out.println(System.currentTimeMillis() - date.getTime());
    }

}


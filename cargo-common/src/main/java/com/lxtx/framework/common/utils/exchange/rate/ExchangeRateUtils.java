package com.lxtx.framework.common.utils.exchange.rate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.constants.PropertiesContants;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.framework.common.utils.http.okhttp.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Czh
 * Date: 2018/8/22 上午11:50
 */
@Slf4j
public class ExchangeRateUtils {

    /**
     * 获取汇率
     *
     * @return
     */
    public Map<String, BigDecimal> getAllExchangeRates() {
        String body = null;
        JSONObject jsonObject = null;
        Map<String, BigDecimal> currencyExchangeRatesMaps = new HashMap<>();
        try {
            body = OkHttpUtil.get(PropertiesUtil.getString(PropertiesContants.EXCHANGE_RATE_BASE_URL));
            JSONArray array = JSONObject.parseArray(body);

            for (int i = 0; i < array.size(); i++) {
                jsonObject = array.getJSONObject(i);
                String keyFrom = jsonObject.getString("from_coin_en");
                String keyTo = jsonObject.getString("to_coin_en");
                currencyExchangeRatesMaps.put(keyFrom + "-" + keyTo, new BigDecimal(jsonObject.getString("rate_res")));
            }
        } catch (Exception e) {
            log.error("获取法币汇率异常 【response body:{}】- 【response jsonObject:{}】", body, jsonObject, e);
        }
        return currencyExchangeRatesMaps;

    }

}
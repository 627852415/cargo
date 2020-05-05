package com.lxtx.framework.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author CaiRH
 * @Date 2018-09-17 10:50
 * @Description BigDecimal 序列化返回字符串
 */
@Slf4j
public class ToStrForBigDecimalSerialize extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        String bigDecimalStr = "";
        if (bigDecimal != null) {
            bigDecimalStr = bigDecimal.toPlainString();
            if(bigDecimalStr.indexOf(".") > 0){
                //正则表达
                //去掉后面无用的零
                bigDecimalStr = bigDecimalStr.replaceAll("0+?$", "");
                //如小数点后面全是零则去掉小数点
                bigDecimalStr = bigDecimalStr.replaceAll("[.]$", "");
            }
        }
        try {
            jsonGenerator.writeString(bigDecimalStr);
        } catch (IOException e) {
            log.error("---> BigDecimal[{}]序列化出现异常", bigDecimal);
        }

    }
}

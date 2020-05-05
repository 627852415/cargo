package com.lxtx.framework.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date日期格式化字符串
 */
public class CustomJsonSerializer extends JsonSerializer<Date> {


    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = bf.format(date);
        jsonGenerator.writeString(format);
    }
}
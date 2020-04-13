package com.lxtx.im.admin.service.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = jp.getText();
        if (StringUtils.isNotBlank(date)) {
            try {
                return format.parse(date);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
package com.lxtx.framework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * <p>
 * 国际工具类
 * </p>
 *
 * @author liboyan
 * @since 2018-08-03
 */
@Slf4j
@Component
public class LocaleUtils implements ApplicationContextAware {
    private static final String KEY_SPLIT = "_";

    public static Locale TH_TH =  new Locale("th", "TH");
    public static Locale KM_KH =  new Locale("km", "KH");

    protected static final ThreadLocal<Locale> threadLocal = new InheritableThreadLocal<>();
    private static ApplicationContext ctx;
    public enum Local {
        en_US(), zh_CN(), zh_TW(), th_TH(), km_KH();
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static Locale getByKey(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("local key required");
        }
        String[] array = key.split(KEY_SPLIT);
        return new Locale(array[0], array[1]);

    }

    public static String getMessage(String messageKey) {
        return getMessage(messageKey, null);
    }

    public static String getMessageNoException(String messageKey) {
        try{
            return getMessage(messageKey, null);
        } catch (Exception e) {
            log.error("获取国际化异常{}", messageKey, e);
        }
        return "";
    }

    public static String getMessage(String messageKey, Object[] obj) {
        Locale locale = LocaleUtils.get();
        if (locale == null) {
            locale = Locale.CHINA;
        }
        return ctx.getMessage(messageKey, obj, locale);
    }

    public static String getMessage(String messageKey, Object[] obj, String locale) {
        String[] array = locale.split(KEY_SPLIT);
        return ctx.getMessage(messageKey, obj, new Locale(array[0], array[1]));
    }

    public static void set(Locale local) {
        threadLocal.set(local);
    }

    public static Locale get() {
        return threadLocal.get();
    }
}
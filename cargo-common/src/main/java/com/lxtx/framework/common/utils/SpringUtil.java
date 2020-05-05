package com.lxtx.framework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-09-02 09:44
 * @Description
 */
@Slf4j
public class SpringUtil {
    private SpringUtil() {
    }

    private static ApplicationContext context = null;

    /**
     * 初始化Spring上下文
     *
     * @param ctx 上下文对象
     */
    public static void initContext(ApplicationContext ctx) {
        if (ctx == null) {
            log.warn("ApplicationContext is null.");
            return;
        }
        context = ctx;
    }


    /**
     * 根据类型获取Bean
     *
     * @param cls Bean类
     * @param <T> Bean类型
     * @return Bean对象
     */
    public static <T> T getBean(Class<T> cls) {
        return context == null ? null : context.getBean(cls);
    }

    /**
     * 根据名称获取Bean
     *
     * @param name Bean名称
     * @return Bean对象
     */
    public static Object getBean(String name) {
        return context == null ? null : context.getBean(name);
    }

    /**
     * 根据Bean名称和类获取Bean对象
     *
     * @param name Bean名称
     * @param cls  Bean类
     * @param <T>  Bean类型
     * @return Bean对象
     */
    public static <T> T getBean(String name, Class<T> cls) {
        return context == null ? null : context.getBean(name, cls);
    }
    
    public static String getActiveProfile() {
    	String[] activeProfiles = context.getEnvironment().getActiveProfiles();
    	if(activeProfiles.length>0) {
    		return activeProfiles[0];
    	}else {
    		return null;
    	}
    }

}

package com.lxtx.framework.common.utils;

import org.springframework.stereotype.Component;

/**
 * RequestUtils
 *
 * @since 2019-04-10
 */
@Component
public class RequestUtils {

    private RequestUtils() {
    }

    protected static final ThreadLocal<RequestInfo> threadLocal = new InheritableThreadLocal<>();

    public static RequestInfo get() {
        return threadLocal.get();
    }

    public static void set(RequestInfo info) {
        threadLocal.set(info);
    }

    public static String getVersionName() {
        RequestInfo requestInfo = threadLocal.get();
        if (requestInfo != null) {
            return  requestInfo.getVersionName();
        }
        return null;
    }

}
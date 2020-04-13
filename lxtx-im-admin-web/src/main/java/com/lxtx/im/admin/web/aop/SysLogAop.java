package com.lxtx.im.admin.web.aop;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @Author liyunhua
 * @Date 2018-10-26 0026
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAop {

    String value() default "";

    /**
     * 监控，增删改-需要发送监控通知，如电报、邮箱、短信
     *
     * @return true：发送，
     * false：不发送
     * 默认false
     */
    boolean monitor() default false;

    /**
     * 打印参数，默认需要
     *
     * @return
     */
    boolean param() default true;
}

package com.lxtx.im.admin.web.config;


import com.alibaba.fastjson.JSON;
import com.lxtx.framework.common.utils.IPAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign调用日志打印 切面
 * @author zkj
 * @date 2018-09-04
 */
@Slf4j
@Component
@Aspect
public class FeignLogAop {

    /**
     * 切入点(使用@FeignClient注解类下的方法)
     */
    @Pointcut("@within(org.springframework.cloud.openfeign.FeignClient)")
    public void FeignLogAop(){}

    /**
     * feign打印请求日志
     * @param joinPoint
     */
    @Before("FeignLogAop()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            StringBuilder sb = new StringBuilder();

            try {
                String uri = request.getRequestURI();
                if (null != uri) {
                    sb.append("【请求URI】").append(uri);
                }
                String ip = IPAddressUtil.getIpAddress(request);
                sb.append("【IP信息】").append(ip);
                sb.append("【请求参数】").append(JSON.toJSONString(joinPoint.getArgs()));
                String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
                log.info("******Feign请求日志******【调用方法】{}{}", method, sb);

            } catch (Exception e) {
                log.error("Feign请求日志异常", e);
            }
        }
    }


    /**
     * feign打印响应日志
     * @param joinPoint
     * @param resp
     */
    @AfterReturning(returning = "resp", pointcut = "FeignLogAop()")
    public void doAfterReturning(JoinPoint joinPoint,Object resp) {
        try {
            String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            log.info("######Feign响应日志######【调用方法】{}【响应结果】{}", method, JSON.toJSONString(resp));
        } catch (Exception e) {
            log.error("Feign响应日志异常",e);
        }
    }
}

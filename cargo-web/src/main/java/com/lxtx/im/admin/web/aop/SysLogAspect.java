package com.lxtx.im.admin.web.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.utils.IPAddressUtil;
import com.lxtx.im.admin.dao.SysLogDao;
import com.lxtx.im.admin.dao.model.SysLog;
import com.lxtx.im.admin.service.SysLogService;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author liyunhua
 * @Date 2018-10-26 0026
 */
@Slf4j
@Aspect
@Component
@Order(5)
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysLogDao sysLogDao;

    private final String FILE_UPLOAD_METHOD = "upload";

    @Pointcut("execution(public * com.lxtx.im.admin.web.api..*.*(..))")
    public void sysLog() {
    }

    @Before("sysLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 记录下请求内容
            String ip = IPAddressUtil.getIpAddress(request);
            String url = request.getRequestURL().toString();
            //获取方法日志注解
            SysLogAop sysLogAop = getControllerMethod(joinPoint);

            String value = sysLogAop == null ? null : sysLogAop.value();

            if (!StringUtils.isEmpty(value)) {
                SysLog sysLog = new SysLog();
                sysLog.setUrl(url);
                sysLog.setIp(ip);
                sysLog.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
                sysLog.setOperation(value);
                //请求的参数
                Object[] args = joinPoint.getArgs();
                String params = null;
                if (args != null && args.length != 0) {
                    params = JSON.toJSONString(args[0]);
                }
                String method = joinPoint.getSignature().getName();
                //文件上传参数太长不插入参数
                if (!FILE_UPLOAD_METHOD.equals(method) && sysLogAop.param()) {
                    sysLog.setParams(params);
                }
                String userName;
                try {
                    userName = ShiroUtils.getUserName();
                } catch (Exception e) {
                    log.info("ShiroUtils获取不到用户，信息，将尝试从参数获取username：{}", params);
                    JSONObject jsonObject = JSONObject.parseObject(params);
                    userName = jsonObject != null ? (String) jsonObject.get("username") : "";
                }

                if (StringUtils.isBlank(userName)) {
                    return;
                }

                sysLog.setUsername(userName);
                sysLogDao.insert(sysLog);

                // 发送监控操作日志消息
                if (sysLogAop.monitor()) {
                    sysLogService.sendNotice(sysLog);
                }
            }
        } catch (Exception e) {
            log.info("记录操作日志异常：{}", e.getMessage());
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     * @author chenyi
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    Annotation annotation = method.getAnnotation(SysLogAop.class);
                    if (annotation != null) {
                        description = method.getAnnotation(SysLogAop.class).value();
                    }
                    break;
                }
            }
        }
        return description;
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     * @author chenyi
     */
    public static SysLogAop getControllerMethod(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        SysLogAop sysLogAop = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    Annotation annotation = method.getAnnotation(SysLogAop.class);
                    if (annotation != null) {
                        sysLogAop = method.getAnnotation(SysLogAop.class);
                    }
                    break;
                }
            }
        }
        return sysLogAop;
    }

}

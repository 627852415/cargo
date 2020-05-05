package com.lxtx.framework.common.log.logback;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.lxtx.framework.common.log.interceptor.LoggerContext;
import org.apache.commons.lang3.StringUtils;

/**
 * 自定义日志输出
 *
 * @author zkj
 * @date 2018/8/8
 */
public class LogMessageConverter extends MessageConverter {

    /**
     * logger 添加输出traceId 跟踪流程
     * @return traceId
     */
    @Override
    public String convert(ILoggingEvent event){
        return StringUtils.isBlank(LoggerContext.getRequestId()) ? "[traceId:]" : "[traceId:"+LoggerContext.getRequestId()+"]";
    }
}
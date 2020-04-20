package com.lxtx.im.admin.web.config;

import com.lxtx.framework.common.log.interceptor.LoggerInterceptor;
import com.lxtx.framework.common.utils.FileUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.web.request.CheckSignatureInterceptor;
import com.lxtx.im.admin.web.request.FeignRequestInterceptor;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


@Configuration
public class Beans {

	@Bean(name = "propertiesUtil")
	public PropertiesUtil propertiesUtil() {
		return new PropertiesUtil();
	}

	@Bean
	@DependsOn("propertiesUtil")
	public LoggerInterceptor loggerInterceptor() {
		return new LoggerInterceptor();
	}

	@Bean
	@DependsOn("propertiesUtil")
	public CheckSignatureInterceptor checkSignatureInterceptor() {
		return new CheckSignatureInterceptor();
	}


	@Bean
	@DependsOn("propertiesUtil")
	public FeignRequestInterceptor FeignRequestInterceptor() {
		return new FeignRequestInterceptor();
	}

    @Bean
    public Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.FULL;
    }

	@Bean
	public FileUtils getFileUtils(){
		return new FileUtils();
	}


}

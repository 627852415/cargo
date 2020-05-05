package com.lxtx.im.admin.web.config;

import com.lxtx.framework.common.log.interceptor.LoggerInterceptor;
import com.lxtx.framework.common.utils.FileUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
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
	public FileUtils getFileUtils(){
		return new FileUtils();
	}


}

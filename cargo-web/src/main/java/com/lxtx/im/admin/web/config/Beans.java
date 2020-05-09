package com.lxtx.im.admin.web.config;

import com.lxtx.framework.common.log.interceptor.LoggerInterceptor;
import com.lxtx.framework.common.utils.FileUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


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


	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
		resolver.setResolveLazily(true);
		resolver.setMaxInMemorySize(40960);
		//上传文件大小 5M 5*1024*1024
		resolver.setMaxUploadSize(5 * 1024 * 1024);
		return resolver;
	}

	@Bean
	public FileUtils getFileUtils(){
		return new FileUtils();
	}


}

package com.lxtx.im.admin.web.config;

import com.lxtx.framework.common.file.utils.QiNiuUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.lxtx.framework.common.interceptor.LocaleInterceptor;
import com.lxtx.framework.common.log.interceptor.LoggerInterceptor;
import com.lxtx.framework.common.security.interceptor.CheckSignatureInterceptor;
import com.lxtx.framework.common.security.interceptor.FeignRequestInterceptor;
import com.lxtx.framework.common.utils.FileUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.framework.common.utils.SmsUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;

import feign.Logger;

/**
 * @author zhiHua chen Created data on 2018/08/07
 */
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
	public RedisCacheUtils<T> redisCacheUtils() {
		return new RedisCacheUtils<T>();
	}

	@Bean
	@DependsOn("propertiesUtil")
	public CheckSignatureInterceptor checkSignatureInterceptor() {
		return new CheckSignatureInterceptor();
	}

	@Bean
	public LocaleInterceptor localeInterceptor() {
		return new LocaleInterceptor();
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

	@Bean
	@DependsOn("propertiesUtil")
	public CheckUsbTokenInterceptor checkUsbTokenInterceptor() {
		return new CheckUsbTokenInterceptor();
	}

	@Bean
	@DependsOn("propertiesUtil")
	public SmsUtil getSmsUtil(){
		SmsUtil smsUtil = new SmsUtil();
		smsUtil.setAccount(PropertiesUtil.getString(PropertiesUtil.YUNSMS_ACCOUNT));
		smsUtil.setPassword(PropertiesUtil.getString(PropertiesUtil.YUNSMS_PASSWORD));
		smsUtil.setUrl(PropertiesUtil.getString(PropertiesUtil.YUNSMS_URL));
		smsUtil.setUsername(PropertiesUtil.getString(PropertiesUtil.ISMS_USERNAME));
		smsUtil.setPasseordIsm(PropertiesUtil.getString(PropertiesUtil.ISMS_PASSEORDISM));
		smsUtil.setBaseurl(PropertiesUtil.getString(PropertiesUtil.ISMS_BASEURL));
		return smsUtil;
	}

	@Bean
	@DependsOn("propertiesUtil")
	public QiNiuUtil qiNiuUtil() {
		QiNiuUtil qiNiuUtil = new QiNiuUtil();
		qiNiuUtil.setAccessKey(PropertiesUtil.getString(PropertiesUtil.QINIU_ACCESSKEY));
		qiNiuUtil.setSecretKey(PropertiesUtil.getString(PropertiesUtil.QINIU_SECRETKEY));
		qiNiuUtil.setBucket(PropertiesUtil.getString(PropertiesUtil.QINIU_BUCKET));
		return qiNiuUtil;
	}
}

package com.lxtx.im.admin.web.config;

import com.lxtx.framework.common.log.interceptor.LoggerInterceptor;
import com.lxtx.im.admin.web.request.CheckSignatureInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * mvc 配置
 *
 * @author zkj
 * @date 2018-08-07
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	private LoggerInterceptor loggerInterceptor;
    @Autowired
	private CheckSignatureInterceptor checkSignatureInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkSignatureInterceptor).addPathPatterns("/**");
		registry.addInterceptor(loggerInterceptor).addPathPatterns("/**");
        registry.addInterceptor(localeChangeInterceptor());
	}


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

}

package com.lxtx;

import com.lxtx.framework.common.log.filter.RepeatedReadRequestFilter;
import com.lxtx.framework.common.utils.SpringUtil;
import com.lxtx.framework.common.utils.ribbon.LxtxRibbonRule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableAsync
@RibbonClients(defaultConfiguration = LxtxRibbonRule.class)
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        SpringUtil.initContext(applicationContext);
	}

	/**
	 * 日志过滤器	 *
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		RepeatedReadRequestFilter repeatedReadRequestFilter = new RepeatedReadRequestFilter();
		registrationBean.setFilter(repeatedReadRequestFilter);
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	@Bean
	public AsyncTaskExecutor taskExecutor(
			@Value("${spring.executor.pool.maxSize}") int maxPoolSize,
			@Value("${spring.executor.pool.coreSize}") int corePoolSize) {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix("async-executor");
		executor.setMaxPoolSize(maxPoolSize);
		executor.setCorePoolSize(corePoolSize);
		executor.setQueueCapacity(1000000);
		executor.setKeepAliveSeconds(60);
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务 sds
		// CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

}

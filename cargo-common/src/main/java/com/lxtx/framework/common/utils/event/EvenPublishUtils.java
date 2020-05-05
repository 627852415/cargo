package com.lxtx.framework.common.utils.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

/**
 * Spring 事件发送工具类 单例
 * @author Dickens.hu
 *
 */
public class EvenPublishUtils implements ApplicationContextAware{
	
	private static ApplicationEventPublisher applicationEventPublisher ;

	private static EvenPublishUtils instance = new EvenPublishUtils() ;
	 
	 private EvenPublishUtils(){} ;
	 
	 
	 public static EvenPublishUtils getInstance(){
		 return instance ;
	 }
	 
     
	 public void publishEvent(ApplicationEvent event){
		 //需要加入 applicationEventPublisher 配置
		 applicationEventPublisher.publishEvent(event);
	 }

	public ApplicationEventPublisher getApplicationEventPublisher() {
		return applicationEventPublisher;
	}
	
	public static void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		EvenPublishUtils.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationEventPublisher = applicationContext;
	}

}

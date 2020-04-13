package com.lxtx.im.admin.service.utils;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池  无界线程池
 */
public class ThreadPool {
	
	
	private int corePoolSize = Runtime.getRuntime().availableProcessors()*50;
	
	private int maximumPoolSize = Runtime.getRuntime().availableProcessors()*50;
	
	private long keepAliveTime =0;
	
	private ThreadPoolExecutor stpe = null;
	
	private ThreadPool(){
		stpe = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
	}
	
	public static final ThreadPool  getInstance(){
		return Singleton.s;
	}
	
	public Future<?> doJob(Runnable command){
		return stpe.submit(command);
	}

	public void shutdown(){
		stpe.shutdown();
	}
	
	public List<Runnable> shutdownNow(){
		return stpe.shutdownNow();
	}
	
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public ThreadPoolExecutor getStpe() {
		return stpe;
	}

	public void setStpe(ThreadPoolExecutor stpe) {
		this.stpe = stpe;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	private static class Singleton{
		private Singleton(){}
		private static final ThreadPool s =new ThreadPool();
	}
}

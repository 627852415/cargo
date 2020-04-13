package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.QuartzFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignQuartzReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 任务调用
 * @author: CXM
 * @create: 2018-10-13 11:53
 */
@FeignClient(value = "lxtx-batch", fallbackFactory = QuartzFeignFallbackFactory.class)
public interface QuartzFeign {

//	// 启动所有有效任务
//	@PostMapping("/quartz/start/all")
//	BaseResult startJob();
//
//	// 启动单个任务
//	@PostMapping("/quartz/start/one")
//	BaseResult startOneJob(FeignQuartzReq quartzReq);
//
//	// 取得某个任务的信息
//	@PostMapping("/quartz/get/info")
//	BaseResult getJobInfo(FeignQuartzReq quartzReq);
//
//	// 修改某个任务的执行时间
//	@PostMapping("/quartz/modifyJob")
//	BaseResult modifyJob(FeignQuartzReq quartzReq);
//
//	// 暂停所有任务
//	@PostMapping("/quartz/pauseAllJob")
//	BaseResult pauseAllJob();
//
//	// 暂停单个任务
//	@PostMapping("/quartz/pauseJob")
//	BaseResult pauseJob(FeignQuartzReq quartzReq);
//
//	// 恢复所有任务
//	@PostMapping("/quartz/resumeAllJob")
//	BaseResult resumeAllJob();
//
//	// 恢复单个任务
//	@PostMapping("/quartz/resumeJob")
//	BaseResult resumeJob(FeignQuartzReq quartzReq);
//
//	// 删除单个任务
//	@PostMapping("/quartz/deleteJob")
//	BaseResult deleteJob(FeignQuartzReq quartzReq);


	// 启动所有有效任务
	@PostMapping("/quartz/v2/start/all")
	BaseResult startJob();

	// 启动单个任务
	@PostMapping("/quartz/v2/start/one")
	BaseResult startOneJob(FeignQuartzReq quartzReq);

	// 取得某个任务的信息
	@PostMapping("/quartz/v2/get/info")
	BaseResult getJobInfo(FeignQuartzReq quartzReq);

	// 修改某个任务的执行时间
	@PostMapping("/quartz/v2/modifyJob")
	BaseResult modifyJob(FeignQuartzReq quartzReq);

	// 暂停所有任务
	@PostMapping("/quartz/v2/pauseAllJob")
	BaseResult pauseAllJob();

	// 暂停单个任务
	@PostMapping("/quartz/v2/pauseJob")
	BaseResult pauseJob(FeignQuartzReq quartzReq);

	// 恢复所有任务
	@PostMapping("/quartz/v2/resumeAllJob")
	BaseResult resumeAllJob();

	// 恢复单个任务
	@PostMapping("/quartz/v2/resumeJob")
	BaseResult resumeJob(FeignQuartzReq quartzReq);

	// 删除单个任务
	@PostMapping("/quartz/v2/deleteJob")
	BaseResult deleteJob(FeignQuartzReq quartzReq);
}

package com.lxtx.im.admin.service.request;

import lombok.Data;

/**
* @description:  停止任务入参
* @author:   CXM
* @create:   2018-10-13 16:55
*/
@Data
public class QuartzStopReq {
	private String taskId;
	private String taskName;
	private String cronExpression;
}

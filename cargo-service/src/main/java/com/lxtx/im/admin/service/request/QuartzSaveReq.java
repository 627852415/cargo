package com.lxtx.im.admin.service.request;

import lombok.Data;

/**
* @description:  定时任务信息入参
* @author:   CXM
* @create:   2018-10-13 16:55
*/
@Data
public class QuartzSaveReq {
	private String taskId;
	private String taskName;
	private String taskDescribe;
	private String cronExpression;
}

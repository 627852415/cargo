package com.lxtx.im.admin.service.request;

import lombok.Data;

/**
* @description:  运行任务入参
* @author:   CXM
* @create:   2018-10-13 16:55
*/
@Data
public class QuartzRunReq {
	private String taskId;
	private String taskName;
	private String cronExpression;
}

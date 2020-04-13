package com.lxtx.im.admin.service.response;

import lombok.Data;
/**
* @description:  定时任务列表返回
* @author:   CXM
* @create:   2018-10-13 16:55
*/
@Data
public class QuartzResp {
	private String taskId;
	private String taskName;
	private String taskDescribe;
	private String cronExpression;
	private Boolean status;
}

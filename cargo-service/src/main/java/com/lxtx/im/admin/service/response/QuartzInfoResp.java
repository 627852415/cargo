package com.lxtx.im.admin.service.response;

import lombok.Data;

/**
* @description:  定时任务信息返回
* @author:   CXM
* @create:   2018-10-13 16:55
*/
@Data
public class QuartzInfoResp {
	private String taskId;
	private String taskName;
	private String taskDescribe;
	private String cronExpression;
	private Boolean isEffect;
	private Boolean status;
}

package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_im_timer_task")
public class AppTimerTask extends BaseModel implements Serializable {
	private static final long serialVersionUID = 2335994772026501592L;
	@TableId
	private String taskId;
	private String taskName;
	private String taskDescribe;
	private String cronExpression;
	private Boolean status;
}

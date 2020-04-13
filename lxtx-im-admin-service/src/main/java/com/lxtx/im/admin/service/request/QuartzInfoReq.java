package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  根据id获取任务信息
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class QuartzInfoReq {
    /**
     * 任务ID
     */
    @NotBlank(message = "任务ID不能为空")
    private String taskId;
}

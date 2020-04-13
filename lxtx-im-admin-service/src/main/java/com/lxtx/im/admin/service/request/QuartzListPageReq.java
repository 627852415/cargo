package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 获取任务管理列表入参
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class QuartzListPageReq extends BasePageReq {
    /**
     * 角色名称
     */
    private String taskDescribe;
}

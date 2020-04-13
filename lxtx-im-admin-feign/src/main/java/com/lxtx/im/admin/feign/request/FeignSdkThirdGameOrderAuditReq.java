package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  获取第三方游戏订单审核类
* @author:   CXM
* @create:   2018-11-30 11:27
*/
@Getter
@Setter
public class FeignSdkThirdGameOrderAuditReq{
    /**
     * 订单ID
     */
    private String id;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 审核意见
     */
    private String auditOpinion ;
}

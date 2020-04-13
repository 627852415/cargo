package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
* @description:  获取第三方游戏订单审核类
* @author:   CXM
* @create:   2018-11-30 11:27
*/
@Getter
@Setter
public class SdkThirdGameOrderAuditReq {
    /**
     * 订单ID
     */
    @NotBlank(message = "订单编号不能为空")
    private String id;

    /**
     * 审核状态
     */
    @NotNull(message = "审核状态不能为空")
    private Integer status;

    /**
     * 审核意见
     */
    private String auditOpinion ;
}

package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @description: 系统提现审核
 * @author: CXM
 * @create: 2018-09-01 13:50
 **/
@Setter
@Getter
public class PlatformWithdrawApplyAuditReq {

    @NotBlank(message = "id不能为空")
    private String id;


    @NotNull(message = "状态不能为空")
    private Integer status;
}

package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class UserStateOperateReq {
    /**
     *  账号,主键
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 账号状态，0:启用，1：禁用，2：冻结
     */
    @NotBlank(message = "状态不能为空")
    private String state;
}

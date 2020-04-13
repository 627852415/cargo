package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Setter
@Getter
public class SysUserModifyReq {
    /**
     * 账号,主键
     */
    @NotBlank(message = "账号不能为空")
    private String userId;

    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 电话
     */
    private String mobile;

    /**
     * 名称
     */
    private String username;

    /**
     * 是否更新操作
     */
    private String isUpdate;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    private String password2;

    /**
     * token编号
     */
    @NotBlank(message = "token编号不能为空")
    private String usbTokenNo;
    /**
     * IP
     */
    @NotBlank(message = "IP不能为空")
    private String ip;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色
     */
    private List<String> roleIdList;
}

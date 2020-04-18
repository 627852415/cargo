package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class UserModifyReq {
    /**
     * 账号,主键
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 0:女 1：男
     */
    private String gender;

    /**
     * 名称
     */
    private String name;

    /**
     * 国际简码
     */
    @NotBlank(message = "国际简码不能为空")
    private String countryCode;

    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码不能为空")
    private String telephone;


}

package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class FeignUserModifyReq {
    /**
     *  账号,主键
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
    private String countryCode;

    /**
     * 电话号码
     */
    private String telephone;

}

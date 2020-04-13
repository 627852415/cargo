package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class UserResetPsdReq {
    /**
     *  账号,主键
     */
    @NotBlank(message = "账号不能为空")
    private String account;

}

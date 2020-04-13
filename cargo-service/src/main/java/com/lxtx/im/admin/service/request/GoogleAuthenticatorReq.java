package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class GoogleAuthenticatorReq {

    @NotBlank(message = "密文不能为空")
    private String secret;
    @NotNull(message = "动态密码不能为空")
    private long qrCode;
}

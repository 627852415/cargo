package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 发送bcb官网登录验证码请求参数封装
 */
@Getter
@Setter
public class SmsSendBcbLoginPWReq {

    /**
     * 国际区号
     */
    @NotBlank
    private String countryCode;

    /**
     * 手机号
     */
    @NotBlank
    private String telephone;

    /**
     * bcb银行卡卡号码
     */
    private String cardNo;

    /**
     * bcb官网登录密码
     */
    private String loginPassword;
}

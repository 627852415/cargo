package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户session信息
 * @Author tnagdy
 */
@Setter
@Getter
public class UserSessionResp {
    private String accessToken;

    private String account;

    private String countryCode;

    private String telephone;

    private String state;

}

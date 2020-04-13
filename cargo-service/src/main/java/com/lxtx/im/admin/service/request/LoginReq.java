package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author tangdy
 * @Date 2018-08-27
 */
@Getter
@Setter
public class LoginReq {
    private String username;
    private String password;

    /**
     * usbToken状态码
     */
    private Integer usbTokenStatus;
}

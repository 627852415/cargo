package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 用户登录请求参数
 *
 * @author liyunhua
 * @date 2018-09-29 0029
 */
@Getter
@Setter
public class SysLoginReq {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;
}

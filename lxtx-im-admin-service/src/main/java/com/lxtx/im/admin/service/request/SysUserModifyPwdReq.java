package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysUserModifyPwdReq {

    /**
     * 密码
     */
    private String comfirPassword;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 旧密码
     */
    private String oldPwd;

}

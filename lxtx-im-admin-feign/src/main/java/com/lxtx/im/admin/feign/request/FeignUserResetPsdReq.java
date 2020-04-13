package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 重置密码
 *
 * @author tangdy
 * @since 2018-08-27
 */
@Getter
@Setter
public class FeignUserResetPsdReq{
    /**
     *  账号,主键
     */
    private String account;
    /**
     * 密码
     */
    private String password;


}

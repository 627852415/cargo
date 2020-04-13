package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignUserInfoReq {

    /**
     * 账号,主键
     */
    private String account;

    /**
     * 名称
     */
    private String userName;

    /**
     * 电话
     */
    private String telephone;


}

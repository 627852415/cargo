package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignInviteRelationReq {

    /**
     * 下级帐号
     */
    private String account;


    /**
     * 上级帐号
     */
    private String parentAccount;


}

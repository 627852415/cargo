package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignInviteDirectRelationReq {

    /**
     * 用户帐号
     */
    private String account;


    /**
     * 用户帐号
     */
    private String otherAccount;


}

package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InviteDirectRelationReq {

    /**
     * 用户帐号
     */
    private String account;


    /**
     * 用户帐号
     */
    private String otherAccount;


}

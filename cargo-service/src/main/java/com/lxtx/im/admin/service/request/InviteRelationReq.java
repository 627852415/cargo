package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InviteRelationReq {

    /**
     * 下级帐号
     */
    private String account;


    /**
     * 上级帐号
     */
    private String parentAccount;


}

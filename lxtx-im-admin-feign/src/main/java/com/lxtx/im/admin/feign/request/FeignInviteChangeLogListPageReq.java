package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignInviteChangeLogListPageReq extends BasePageReq {

    /**
     * 被转移用户
     */
    private String changeAccount;

    /**
     * 接受转移用户
     */
    private String acceptAccount;

}

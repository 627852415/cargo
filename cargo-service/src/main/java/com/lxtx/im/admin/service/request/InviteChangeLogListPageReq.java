package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InviteChangeLogListPageReq extends BasePageReq {

    /**
     * 被转移用户
     */
    private String changeAccount;

    /**
     * 接受转移用户
     */
    private String acceptAccount;

}

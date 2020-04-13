package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RebatePlayerGameDetailPageReq extends BasePageReq {

    /**
     * 返佣用户账号
     */
    private String account;
    /**
     * 返佣时间
     */
    private String reportDate;
    /**
     * 玩家账号
     */
    private String playerAccount;
    /**
     * 玩家名称
     */
    private String playerUserName;

}

package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignRebatePlayerGameDetailPageReq extends BasePageReq {

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

}

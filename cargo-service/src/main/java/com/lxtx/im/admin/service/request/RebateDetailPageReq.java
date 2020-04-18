package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RebateDetailPageReq extends BasePageReq {

    /**
     * 返佣用户账号
     */
    private String account;
    /**
     * 返佣时间
     */
    private String reportDate;
    /**
     * 返佣用户名称
     */
    private String userName;
    /**
     * 玩家名称
     */
    private String playerUserName;
    /**
     * 玩家账号
     */
    private String playerAccount;
    /**
     * 玩家手机号
     */
    private String playerTelephone;

    /**
     * 游戏id
     */
    private String taskId;

    /**
     * 返佣类型
     */
    private Integer rebateType;

}

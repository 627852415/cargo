package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FeignRebateDetailPageReq extends BasePageReq {

    /**
     * 返佣用户账号
     */
    private String account;
    /**
     * 返佣时间
     */
    private String reportDate;
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

    private List<String> playerAccountList;

    /**
     * 返佣类型
     */
    private Integer rebateType;

}

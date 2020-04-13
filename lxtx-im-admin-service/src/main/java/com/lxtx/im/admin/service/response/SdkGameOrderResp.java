package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 第三方游戏转账订单表
 *
 * @author tangdy
 * @since 2018-11-29
 */
@Getter
@Setter
public class SdkGameOrderResp {

    private String id;
    /**
     * 转出用户ID
     */
    private String userId;
    /**
     * 数量
     */
    private BigDecimal amount;
    /**
     * 状态【1：待审核，2：通过，3:拒绝】
     */
    private Integer status;

    /**
     * 审核意见
     */
    private String auditOpinion;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 交易说明/备注6X
     */
    private String transferRemark;
    /**
     * 转账类型【1：好友转账】
     */
    private Integer type;
    /**
     * 币种ID
     */
    private String coinId;
    /**
     * 接受用户ID
     */
    private String receiverId;

    /**
     * 币种名称
     */
    private String coinName;

    /**
     * 备注
     */
    public String remarks;
    /**
     * 创建时间
     */
    public Date createTime;
    /**
     * 创建人
     */
    public String createBy;
    /**
     * 更新时间
     */
    public Date updateTime;
    /**
     * 更新人
     */
    public String updateBy;

    /**
     * 转出方用户名称
     */
    public String userName;

    /**
     * 转入方用户名称
     */
    public String receiverUserName;

}

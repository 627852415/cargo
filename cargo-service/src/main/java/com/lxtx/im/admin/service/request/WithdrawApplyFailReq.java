package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class WithdrawApplyFailReq extends BasePageReq {

    /**
     * 状态【0：待处理，1：处理失败，2：处理成功】
     */
    private Integer status;
    /**
     * 状态【1：6x，2：余额宝】
     */
    private Integer type;

    /**
     * 币种ID->币种名称
     */
    private String coinName;
    /**
     * 用户ID->用户名称
     */
    private String userName;
    /**
     * 交易编号
     */
    private String transferNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date updateTime;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 币种ID
     */
    private String coinId;

    private String applyId;

}

package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 资金入账参数类
 *
 * @author CaiRH
 * @since 2019-11-22
 */
@Getter
@Setter
public class TransactionRechargePageReq extends BasePageReq {

//    ID，用户钱包ID，用户昵称，转出地址，到账地址，币种，外部交易编号，外部订单ID，类型，交易时间

    /**
     * 主键ID
     */
    private String id;
    /**
     * 钱包用户ID
     */
    private String walletUserId;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 转出地址
     */
    private String fromAddr;
    /**
     * 到账地址
     */
    private String toAddr;
    /**
     * 币种
     */
    private String coinName;
    /**
     * 外部交易编号
     */
    private String thirdPartyTransferNum;
    /**
     * 外部订单ID
     */
    private String thirdPartyOrderId;
    /**
     * 类型【1：站内提币，2：链上充值，3：OTC充值】
     */
    private Integer type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date updateTime;
    
    /**
     * 平台用户手机号的国际简码
     */
    private String countryCode;

    /**
     * 电话
     */
    private String telephone;
}

package com.lxtx.framework.common.utils.yeb.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * author lin hj on 2019/3/29
 * 订单流水
 */
@Data
public class YebOrderDetailRes implements Serializable {


    /**
     * 订单编号，余额宝后台分配的。
     */
    private String id;
    /**
     * 第三方的流水编号
     */
    private String serial;

    private String orderId;

    /**
     * 商户下面用户id。
     */
    private String userId;
    /**
     * 存款时，为用户的上级地址(默认为商户地址)。取款时，为收款地址
     */
    private String address;
    /**
     * 订单状态，所有类型的订单status为100的时候说明此订单完成
     */
    private int state;
    /**
     * 交易类型：分4种，deposit表示余额宝存款；withdraw_principal表示提取余额宝本金；withdraw_interest表示提取余额宝收益；withdraw_chain表示提取链上的资金。
     */
    private String transType;
    /**
     * 币种名称，例如DC。
     */
    private String coinType;
    /**
     * 金额。
     */
    private String amount;
    /**
     * 订单生成时间。
     */
    private Date time;
    private String remark;
    /**
     * 本次交易的区块链上交易hash凭证。
     */
    private String txHash;
    /**
     * 订单修改时间
     */
    private String modifyTime;
    private String mchId;
}

package com.lxtx.im.admin.feign.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopGateRechargePaywaySaveReq {

    private String id;

    /**
     * 类型【1、微信扫码支付；2、支付宝扫码支付；3、银行卡；】
     */
    private Integer payWay;

    /**
     * 支付方式名称国际化
     */
    private String payWayDescKey;

    /**
     * logo
     */
    private String logo;

    /**
     * 操作金额最小值
     */
    private BigDecimal minAmount;

    /**
     * 操作金额最大值
     */
    private BigDecimal maxAmount;

    /**
     * 内部手续费
     */
    private BigDecimal innerFee;

    /**
     * 第三方手续费
     */
    private BigDecimal thirdFee;

    /**
     * 开关：0：关闭 1：开启
     */
    private boolean enable;
    
    /**
     * 币Id
     */
    private String coinId;
    
    /**
     * 第三方币种名称
     */
    private String thirdCoinName;
}

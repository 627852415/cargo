package com.lxtx.im.admin.service.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopgateRechargePayWayResp {

    private String id;

    private String payName;
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

    private String strMinAmount;
    /**
     * 操作金额最大值
     */
    private BigDecimal maxAmount;

    private String strMaxAmount;
    /**
     * 内部手续费
     */
    private BigDecimal innerFee;

    private String strInnerFee;
    /**
     * 第三方手续费
     */
    private BigDecimal thirdFee;

    private String strThirdFee;
    /**
     * '开关：0：关闭 1：开启'
     */
    private Boolean enable;
    
    /**
     * 币Id
     */
    private String coinId;
    
    /**
     * 币种
     */
    private String coinName;
    
    /**
     * 第三方币种名称
     */
    private String thirdCoinName;
}

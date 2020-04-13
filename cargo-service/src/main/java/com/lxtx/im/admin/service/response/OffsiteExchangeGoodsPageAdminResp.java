package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 11:15
 * @Description
 */
@Getter
@Setter
public class OffsiteExchangeGoodsPageAdminResp {
    private String goodsId;

    /**
     * 发布兑换的钱包用户id
     */
    private String userId;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 商家联系方式
     */
    private String fullTelephone;


    /**
     * 用户昵称—对应t_im_user的name
     */
    private String name;

    /**
     * 目标金额
     */
    private BigDecimal targetAmount;
    /**
     * 商家返利金额
     */
    private BigDecimal merchantRebateAmount;

    /**
     * 平台利润
     */
    private BigDecimal platformRebateAmount;
    /**
     * 总金额
     */
    private String totaltAmount;
    /**
     * 交易量
     */
    private String totaltSourceAmount;
    /**
     * 成交金额
     */
    private String transactionAmount;

    /**
     * 完成进度
     */
    private String progressRate;

    /**
     *  限额范围
     */
    private String limitRange;
    /**
     * 目标币种id
     */
    private String targetCurrency;

    /**
     * 换汇金额
     */
    private BigDecimal sourceAmount;

    /**
     * 换汇币种id
     */
    private String sourceCurrency;

    /**
     * 最小限额
     */
    private BigDecimal minAmount;

    /**
     * 最大限额
     */
    private BigDecimal maxAmount;

    /**
     * 发布兑换时的汇率
     */
    private BigDecimal exchangeRate;

    /**
     * 下架时间
     */
    private Date pullOffTime;

    /**
     * 付款方式 1:现金付款
     */
    private Integer payType;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否下架标识 0:否 1:是
     */
    private Integer pullOffFlag;

    /**
     * 剩余可兑换金额
     */
    private BigDecimal remainAmount;

    private Date createTime;
    /**
     * 多少天后下架
     */
    private Integer pullOffDay;
}

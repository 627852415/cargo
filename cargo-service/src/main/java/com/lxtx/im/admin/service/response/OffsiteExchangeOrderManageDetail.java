package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OffsiteExchangeOrderManageDetail {
	/**
	 * 线下换汇订单号
	 */
	private String orderId;

	/**
	 * 编号
	 */
	private String goodsId;

	/**
	 * 买家的用户id
	 */
	private String userId;

	/**
	 * 买家的手机号
	 */
	private String buyerTelephone;
	/**
	 * 卖家的用户id
	 */
	private String sellerUserId;

	/**
	 * 卖家的手机号
	 */
	private String sellerTelephone;
	/**
	 * 卖家地址
	 */
	private String address;

	/**
	 * 目标金额
	 */
	private BigDecimal targetAmount;

	/**
	 * 目标币种名称
	 */
	private String targetCurrency;

	/**
	 * 换汇金额
	 */
	private BigDecimal sourceAmount;
	/**
	 * 换汇币种名称
	 */
	private String sourceCurrency;

	/**
	 * 下单时的汇率
	 */
	private BigDecimal exchangeRate;

	/**
	 * 实际汇率
	 */
	private BigDecimal realExchangeRate;
	/**
	 * 实际汇率字符串： 1 USD = 6.5 CNY
	 */
	private String realExchangeRateStr;

	/**
	 * 商家返利金额
	 */
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal merchantRebateAmount;

	/**
	 * 平台利润
	 */
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal platformRebateTotal;
	/**
	 * 平台利润 30 CNY ≈ 5 USD
	 */
	private String platformRebateTotalStr;


	/**
	 * 支付状态 1:未付款 2:已完成 3:已取消
	 */
	private Integer status;
	/**
	 * 是否产生投诉
	 */
	private Boolean complainted;
	/**
	 * 是否支付订单
	 */
	private Boolean payed = false;

	/**
	 * 支付状态：未付款、已完成、已取消
	 */
	private String statusStr;

	/**
	 * 支付时间/完成时间/取消时间
	 */
	private Date operateTime;

	/**
	 * 取消/结束备注
	 */
	private String operateRemark;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 汇率
	 */
	private String rate;
	/**
	 * 交易结束时间
	 */
	private Date expireTime;

	/**
	 * 完成时间
	 */
	private Date updateTime;

	/**
	 * 付款方式 1:现金支付  2：银行卡 3：paypal 4：secret支付
	 * EnumPayType
	 */
	private String payTypeName;
    /**
     * 邀请返利
     */
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal inviteRebateAmount;

	private Boolean usePlus;

	/**
	 * 手续费
	 */
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal commission;
}
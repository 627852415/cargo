package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.im.admin.service.utils.CustomJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
* @description:  后台线下订单返回
* @author:   CXM
* @create:   2019-04-22 17:00
*/
@Getter
@Setter
public class OffsiteExchangeOrderResp {

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
	 * 买家电话
	 */
	private String buyerTelephone;
	/**
	 * 卖家的用户id
	 */
	private String sellerUserId;
	/**
	 * 卖家电话
	 */
	private String sellerTelephone;
	
	/**
	 * 目标金额
	 */
	private String targetAmount;

	/**
	 * 目种币种id
	 */
	private String targetCurrencyId;
	
	/**
	 * 目种数量名称:100DC
	 */
	private String targetCurrency;
	
	/**
	 * 换汇金额
	 */
	private String sourceAmount;
	
	/**
	 * 换汇币种数量名称：100DC
	 */
	private String sourceCurrency;

	/**
	 * 已返利
	 */
	private String merchantRebateAmount;


	/**
	 * 平台利润
	 */
	private String platformRebateAmount;

	/**
	 * 换汇币种id
	 */
	private String sourceCurrencyId;

	/**
	 * 支付状态 1:未付款 2:已完成 3:已取消
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	@JsonSerialize(using = CustomJsonSerializer.class)
	public Date createTime;
}

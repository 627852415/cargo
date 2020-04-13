package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
* @description:  后台线下订单下载
* @author:   CXM
* @create:   2019-04-22 17:00
*/
@Getter
@Setter
public class OffsiteExchangeOrderDownloadResp {

	/**
	 * 线下换汇订单号
	 */
	@ExcelField(name = "订单编号", orderBy = "1")
	private String orderId;

	/**
	 * 买家账号
	 */
	@ExcelField(name = "买家账号", orderBy = "2")
	private String buyerAccountId;

	/**
	 * 买家昵称
	 */
	@ExcelField(name = "买家昵称", orderBy = "3")
	private String buyerName;

	/**
	 * 商家账号
	 */
	@ExcelField(name = "商家账号", orderBy = "4")
	private String merchantAccountId;

	/**
	 * 商家昵称
	 */
	@ExcelField(name = "商家昵称", orderBy = "5")
	private String merchantName;

	/**
	 * 交易量
	 */
	@ExcelField(name = "买家获得", orderBy = "6")
	private BigDecimal targetAmount;

	/**
	 * 交易量单位
	 */
	@ExcelField(name = "单位", orderBy = "7")
	private String targetAmountCurrency;

	/**
	 * 交易额
	 */
	@ExcelField(name = "买家支付", orderBy = "8")
	private BigDecimal sourceAmount;

	/**
	 * 交易额单位
	 */
	@ExcelField(name = "单位", orderBy = "9")
	private String sourceAmountCurrency;

	/**
	 * 汇率
	 */
	@ExcelField(name = "汇率", orderBy = "10")
	private BigDecimal exchangeRate;

	/**
	 * 已返利
	 */
	@ExcelField(name = "已返利", orderBy = "11")
	private BigDecimal merchantRebateAmount;

	/**
	 * 已返利单位
	 */
	@ExcelField(name = "单位", orderBy = "12")
	private String merchantRebateAmountCurrency;

	/**
	 * 平台利润
	 */
	@ExcelField(name = "平台利润", orderBy = "13")
	private BigDecimal platformRebateAmount;

	/**
	 * 平台利润单位
	 */
	@ExcelField(name = "单位", orderBy = "14")
	private String platformRebateAmountCurrency;

	/**
	 * 付款方式
	 */
	@ExcelField(name = "付款方式", orderBy = "15")
	private String paymentMethod;

	/**
	 * 收款方式
	 */
	@ExcelField(name = "收款方式", orderBy = "16")
	private String receiveMethod;

	/**
	 * 交易时间
	 */
	@ExcelField(name = "交易时间", orderBy = "17")
	private Date createTime;

	/**
	 * 完成时间
	 */
	@ExcelField(name = "完成时间", orderBy = "18")
	private Date operateTime;

	/**
	 * 支付状态 1:未付款 2:已完成 3:已取消 4:买家已付款
	 */
	@ExcelField(name = "支付状态", orderBy = "19")
	private String status;

}

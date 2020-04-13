package com.lxtx.im.admin.service.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import com.lxtx.im.admin.service.utils.ExcelField;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @Description 钱包转账
 * @author qing 
 * @date: 2019年11月20日 下午6:15:54
 */
@Getter
@Setter
public class ScanPayResp {
	
	//ID
	@ExcelField(name = "ID", orderBy = "1")
	private String id;
	
	//付款用户钱包ID
	@ExcelField(name = "付款用户钱包ID", orderBy = "2")
	private String userId;
	
	//付款用户昵称
	@ExcelField(name = "付款用户昵称", orderBy = "3")
	private String userName;
	
	/**
	 * 付款用户 国际简码/区码
	 */
	@ExcelField(name = "付款用户 国际简码/区码", orderBy = "4")
	private String payUserCountryCode;
	
	/**
	 * 付款用户手机号
	 */
	@ExcelField(name = "付款用户手机号", orderBy = "5")
	private String payUserPhone;
	
	@ExcelField(name = "商家钱包ID", orderBy = "6")
	private String merchantUserId;

	@ExcelField(name = "商家昵称", orderBy = "7")
	private String merchantUserName;
	
	/**
	 * 商家 国际简码/区码
	 */
	@ExcelField(name = "商家 国际简码/区码", orderBy = "8")
	private String merchantUserCountryCode;
	
	/**
	 * 商户用户手机号
	 */
	@ExcelField(name = "商户用户手机号", orderBy = "9")
	private String merchantUserPhone;
	
	//是否内部地址
	private String typeVaule;
	
	//页面状态显示
	@ExcelField(name = "状态", orderBy = "10")
	private String statusVaule;
	
	//手续费
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal fee;
	
	//支付币种
	@ExcelField(name = "支付币种", orderBy = "11")
	private String payCoinName;
	
	//支付金额
	@ExcelField(name = "支付金额", orderBy = "12")
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal payAmount;
	
	//交易时间
	@ExcelField(name = "交易时间", orderBy = "13")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	
	//更新时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;
	
	//备注
	private String receiptRemarks;

	private Integer status;
	
	private Integer type;
	
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal receiptAmount;
	
	private String payCoinId;
	
	private String receiptCoinId;
	
	private String receiptCoinName;
	
	
}

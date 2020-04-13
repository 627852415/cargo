package com.lxtx.im.admin.service.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lxtx.im.admin.feign.request.BasePageReq;

import lombok.Getter;
import lombok.Setter;

/**
 * 扫码付款数据列表请求
 *
 */
@Getter
@Setter
public class ScanPayListPageReq extends BasePageReq {

	private String id;

	private String userId;
	
	private String userName;
	
	private String merchantUserId;
	
	private String merchantUserName;
	
	/**
	 * 支付币种ID
	 */
	private String payCoinId;
	/**
	 * 收款币种ID
	 */
	private String receiptCoinId;
	
	/**
	 * 付款用户 国际简码/区码
	 */
	private String payUserCountryCode;
	
	/**
	 * 商家 国际简码/区码
	 */
	private String merchantUserCountryCode;
	
	/**
	 * 付款用户手机号
	 */
	private String payUserPhone;
	
	/**
	 * 商户用户手机号
	 */
	private String merchantUserPhone;

	// 状态
	private Integer status;

	// 交易时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTimeStart;

	// 交易时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTimeEnd;
}

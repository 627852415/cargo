package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

/**
 * 扫码付款数据列表
 *
 */
@Getter
@Setter
public class FeignScanPayListPageReq extends BasePageReq {

	private String id;

	private String userId;
	
	private String merchantUserId;
	
	/**
	 * 支付币种ID
	 */
	private String payCoinId;
	/**
	 * 收款币种ID
	 */
	private String receiptCoinId;

	// 状态
	private Integer status;

	// 交易时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTimeStart;

	// 交易时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTimeEnd;
	
	//转出用户钱包ID
	private List<String> userIds;
		
	///商家钱包ID
	private List<String> merchantUserIds;
	/**
	 * 是否柬埔寨演示账号
	 */
	private boolean isKHShowAccount;
}

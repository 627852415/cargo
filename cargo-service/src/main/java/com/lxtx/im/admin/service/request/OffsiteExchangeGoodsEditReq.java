package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class OffsiteExchangeGoodsEditReq {

	/**
	 * userId
	 */
	@NotBlank(message = "userId 不能为空")
	private String userId;

	/**
	 * merchantId
	 */
	@NotBlank(message = "merchantId 不能为空")
	private String merchantId;

	/**
	 * 编号
	 */
	@NotBlank(message = "编号不能为空")
	private String goodsId;
	
}

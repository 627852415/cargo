package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumOffsiteExchangeOrderStatus {

	UNPAID(1, "未支付"), 
	COMPLETED(2, "已完成"), 
	CANCELLED(3, "已取消"),
	BUYER_PAID(4, "买家已付款");

	private Integer code;
	private String description;

	public static EnumOffsiteExchangeOrderStatus find(Integer code) {
		if (code == null) {
			return null;
		}

		for (EnumOffsiteExchangeOrderStatus element : EnumOffsiteExchangeOrderStatus.values()) {
			if (element.getCode().equals(code)) {
				return element;
			}
		}
		return null;
	}
}

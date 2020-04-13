package com.lxtx.im.admin.service.request;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 扫码付款数据详情请求
 *
 */
@Getter
@Setter
public class ScanPayDetailReq {

	@NotBlank(message = "id不能为空")
	private String id;

}

package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 代发工资数据详情请求
 *
 */
@Getter
@Setter
public class SalaryInDetailReq {

	@NotBlank(message = "id不能为空")
	private String id;

}

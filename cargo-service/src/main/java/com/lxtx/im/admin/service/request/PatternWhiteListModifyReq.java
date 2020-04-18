package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class PatternWhiteListModifyReq {

	@NotNull(message = "account不能为空")
    private String account;

	@NotNull(message = "开启标识不能为空")
    private Integer flag;
	
}

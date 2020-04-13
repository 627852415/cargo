package com.lxtx.im.admin.service.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatternWhiteListModifyReq {

	@NotNull(message = "account不能为空")
    private String account;

	@NotNull(message = "开启标识不能为空")
    private Integer flag;
	
}

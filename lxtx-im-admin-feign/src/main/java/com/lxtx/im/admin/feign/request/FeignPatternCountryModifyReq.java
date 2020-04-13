package com.lxtx.im.admin.feign.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignPatternCountryModifyReq {

	@NotNull(message = "法币选择记录ID不能为空")
    private String legalId;
	
	@NotNull(message = "数字币选择记录ID不能为空")
	private String virtualId;

	@NotNull(message = "法币是否开启不能为空")
    private Integer legalValid;
	
	@NotNull(message = "数字币是否开启不能为空")
	private Integer virtualValid;
    
}

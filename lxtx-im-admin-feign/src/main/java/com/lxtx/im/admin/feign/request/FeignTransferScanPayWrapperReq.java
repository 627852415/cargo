package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeignTransferScanPayWrapperReq {
private String id;
	
    /**
     * 平台用户名称
     */
    private String name;
    
    /**
     * 平台用户手机号的国际简码
     */
    private String countryCode;

    /**
     * 平台用户手机号
     */
    private String telephone;
}

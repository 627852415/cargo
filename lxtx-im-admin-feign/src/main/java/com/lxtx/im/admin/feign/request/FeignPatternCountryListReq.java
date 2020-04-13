package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeignPatternCountryListReq extends BasePageReq {

	/**
     * 国家中文名称
     */
    private String cnName;

}

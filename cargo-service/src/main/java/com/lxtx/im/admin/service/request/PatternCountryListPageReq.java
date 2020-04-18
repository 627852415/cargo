package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatternCountryListPageReq extends BasePageReq {

    /**
     * 国家中文名称
     */
    private String cnName;

}

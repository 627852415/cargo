package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  根据key获取字典表的value
* @author:   CXM
* @create:   2018-10-16 17:09
*/
@Setter
@Getter
public class FeignDictGetValueReq {
    /**
     * 域
     */
    private String domain;
    /**
     * key值
     */
    private String ikey;

    public FeignDictGetValueReq(String domain, String ikey) {
        this.domain = domain;
        this.ikey = ikey;
    }
}

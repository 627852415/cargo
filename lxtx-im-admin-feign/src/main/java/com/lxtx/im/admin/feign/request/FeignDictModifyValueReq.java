package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  根据domain和key修改对应的值
* @create:   2018-04-09
*/
@Setter
@Getter
public class FeignDictModifyValueReq {
    /**
     * 域
     */
    private String domain;
    /**
     * key值
     */
    private String ikey;
    /**
     * value值
     */
    private String value;

}

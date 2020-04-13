package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  删除币种手续费参数类
* @author:   tangdy
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class FeignCoinChargeDeleteReq {
    /**
     * ID
     */
    private String id;
}

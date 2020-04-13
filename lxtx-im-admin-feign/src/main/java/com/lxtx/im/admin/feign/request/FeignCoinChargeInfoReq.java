package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  获取币种手续费信息参数类
* @author:   tangdy
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class FeignCoinChargeInfoReq {
    /**
     * 字典ID
     */
    private String id;
}

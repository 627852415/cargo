package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  结束交易
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class FeignOffisteExchangeOrderEndReq {
    /**
     * 订单ID
     */
    private String orderId;
}

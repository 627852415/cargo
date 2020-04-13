package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  线下汇换订单详情
* @author:   CXM
* @create:   2019-04-19 9:55
*/
@Getter
@Setter
public class FeignOffsiteExchangeOrderDetailReq {
    /**
     * 订单编号
     */
    private String orderId;
}

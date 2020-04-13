package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author LiuLP
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
public class FeignQueryOtcOrderListReq extends BasePageReq {

    /**
     * 订单状态
     *
     */
    private Integer state1;


    /**
     * 订单号
     */
    private String orderNum;




}

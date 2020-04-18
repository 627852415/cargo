package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
 * otc订单类
 *
 * @author LiuLP
 * @since 2018-09-27
 */
@Getter
@Setter
public class OtcOrderReq extends BasePageReq {
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

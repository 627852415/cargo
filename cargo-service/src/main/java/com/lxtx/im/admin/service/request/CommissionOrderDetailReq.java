package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 分佣订单详情表
 * </p>
 *
 * @author 
 * @since 2020-03-07
 */
@Getter
@Setter
public class CommissionOrderDetailReq extends BasePageReq {

    /**
     * 订单ID
     */
    private String id;

    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 订单用户ID
     */
    private String orderUserId;
    /**
     * 订单返佣金额
     */
    private BigDecimal orderAmount;
}

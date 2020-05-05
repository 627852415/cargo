package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 闪兑直接下单接口请求参数
 *
 * @author CaiRH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastExchangeGenerateOrderReq {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 要支付的币种
     */
    private String payCoin;
    /**
     * 要获得的币种
     */
    private String gotCoin;
    /**
     * 下单的数量
     */
    private BigDecimal amount;
    /**
     * 调用方/平台订单号
     */
    private String orderNum;
}

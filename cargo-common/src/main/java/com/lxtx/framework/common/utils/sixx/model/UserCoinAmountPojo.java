package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 当前用户可用于支付的币种和金额
 *
 * @author CaiRH
 */
@Data
public class UserCoinAmountPojo {
    /**
     * 币种名称
     */
    private String coin;
    /**
     * 币种数量余额
     */
    private BigDecimal amount;

}

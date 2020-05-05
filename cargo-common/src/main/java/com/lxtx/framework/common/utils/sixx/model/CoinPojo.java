package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 币种信息
 *
 * @author CaiRH
 */
@Data
public class CoinPojo {
    /**
     * 币种名称
     */
    private String coin;
    /**
     * 每笔最小限额
     */
    private BigDecimal withdrawLeast;
    /**
     * 每天最大限额
     */
    private BigDecimal withdrawMaxPerday;
    /**
     * 精度
     */
    private String precision;
    /**
     * 手续费
     */
    private BigDecimal withdrawFee;

}

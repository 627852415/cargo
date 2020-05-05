package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author cctv
 */
@Data
public class BalanceAmountResultPojo {
    /**
     * 冻结金额
     */
    private BigDecimal frozenAmount;
    /**
     * 余额
     */
    private BigDecimal leftAmount;
    /**
     * 6x系统分配的钱包地址
     */
    private String assignAddr;
    /**
     * 绑定的钱包地址
     */
    private String boundAddr;
    /**
     * 备注
     */
    private String remark;
    /**
     * 币种名字
     */
    private String coin;

}

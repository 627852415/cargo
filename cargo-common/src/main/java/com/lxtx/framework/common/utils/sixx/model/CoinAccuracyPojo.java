package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

/**
 * 币种精度
 *
 * @author cctv
 */
@Data
public class CoinAccuracyPojo {
    /**
     * 币种名称
     */
    private String coin;
    /**
     * 每笔最小限额
     */
    private String withdrawLeast;
    /**
     * 每天最大限额
     */
    private String withdrawMaxPerday;
    /**
     * 精度
     */
    private String precision;
    /**
     * 手续费
     */
    private String withdrawFee;

}

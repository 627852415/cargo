package com.lxtx.framework.common.utils.exchange.rate.niu.config;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author CaiRH
 * @since 2018-08-28
 */
@Getter
@Setter
public class CToCPriceResp {

    /**
     * 卖出价格
     */
    private BigDecimal c2cSellPrice;

    /**
     * 买入价格
     */
    private BigDecimal c2cBuyPrice;

}
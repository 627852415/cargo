package com.lxtx.framework.common.utils.exchange.rate.niu.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author CaiRH
 * @since 2018-08-22
 */
@Getter
@Setter
public class ConfigParams {

    /**
     * 统一URL前缀
     */
    private String baseUrl;

    /**
     * 获取C2C价格URL
     */
    private String c2cPriceUrl;

    /**
     * 获取指定币对的交易价格URL
     */
    private String investDoubleUrl;

}
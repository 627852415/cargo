package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @since 2019-06-03
 */
@Getter
@Setter
public class OffsiteExchangeWaveAreaRateListResp {

    /**
     * 浮动汇率表id
     */
    private String waveRateId;
    /**
     * 国际简码
     */
    private String countryCode;
    /**
     * 地区名称
     */
    private String countryName;
    /**
     * 汇率类型【0：百分比，1：固定值】
     */
    private Integer rateType;
    /**
     * 浮动汇率
     */
    private BigDecimal waveRate;
    /**
     * 实时汇率
     */
    private BigDecimal realTimeRate;
    /**
     * 兑换汇率 = 实时汇率 * (1 - 浮动汇率)
     */
    private BigDecimal exchangeRate;
    /**
     * 兑换的币种名称
     */
    private String exchangeCoinName;
    /**
     * 支付的币种名称
     */
    private String payCoinName;

    /**
     * 支付方式id信息
     */
    private List<OffsiteExchangeWaveAreaRateRebateSimpleResp> rebateList;
}

package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 换汇地区波动汇率配置表
 * @since 2019-06-03
 */
@Getter
@Setter
public class OffsiteExchangeWaveRateAreaResp {

    private String id;
    /**
     * 换汇浮动汇率表id
     */
    private String waveRateId;
    /**
     * 国际简码
     */
    private String countryCode;
    /**
     * 最低限额
     */
    private BigDecimal minAmount;
    /**
     * 波动汇率 e.g. 0.05（百分之五）
     */
    private BigDecimal waveRate;
    /**
     * 汇率类型【0：百分比，1：固定值】
     */
    private Integer rateType;

    /**
     * 支付方式Ids
     */
    private List<String> rebateIdList;
}

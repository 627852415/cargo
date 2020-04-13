package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 换汇波动汇率配置表
 *
 * @author CaiRh
 * @since 2019-05-24
 */
@Getter
@Setter
public class OffsiteExchangeWaveRateModelResp {

    private String id;

    /**
     * 源币种id
     */
    private String sourceCoinId;
    /**
     * 目标币种id
     */
    private String targetCoinId;

    /**
     * 最低限额
     */
    private String minAmount;
    /**
     * 汇率类型【0：百分比，1：固定值】
     */
    private Integer rateType;
    /**
     * 参考汇率
     */
    private String referenceRate;
    /**
     * 波动汇率 e.g. 0.05（百分之五）
     */
    private String waveRate;

    /**
     * 最终汇率
     */
    private String exchangeRate;
    
    private Integer sort;

    /**
     * 浮动范围
     */
    private BigDecimal floatScope;

    /**
     * 参考汇率模式（逻辑用EnumOffsiteExchangeRateModel）
     */
    private Integer exchangeRateModel;

    /**
     * 排序规则【0：优先高汇率，1：优先低汇率】
     */
    private Integer sortRule;

    /**
     * 商家收款方式ids
     */
    private List<String> rebateIdList;

    /**
     * 商家支付方式Ids
     */
    private List<String> merchantOutPaymentIdList;
}

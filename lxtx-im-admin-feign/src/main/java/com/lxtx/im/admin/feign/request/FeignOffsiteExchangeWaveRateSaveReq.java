package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


/**
 * 换汇汇率保存参数封装
 *
 * @author CaiRH
 * @since 2019-05-24
 */
@Getter
@Setter
public class FeignOffsiteExchangeWaveRateSaveReq {

    private String id;

    /**
     * 源币种
     */
    private String sourceCoinId;
    /**
     * 目标币种
     */
    private String targetCoinId;
    /**
     * 最低限额
     */
    private BigDecimal minAmount;
    /**
     * 汇率类型【0：百分比，1：固定值】
     */
    private Integer rateType;
    /**
     * 参考汇率
     */
    private BigDecimal referenceRate;
    /**
     * 浮动汇率
     */
    private BigDecimal waveRate;
    /**
     * 换汇汇率
     */
    private BigDecimal exchangeRate;
    
    private Integer sort;

    /**
     * 商家收款方式ids
     */
    private List<String> rebateIds;

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
     * 商家支付方式Ids
     */
    private List<String> merchantOutPaymentIds;

}

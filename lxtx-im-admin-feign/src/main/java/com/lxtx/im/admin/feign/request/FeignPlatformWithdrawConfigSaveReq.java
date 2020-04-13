package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 保存平台提款配置
 *
 * @author CaiRH
 */
@Setter
@Getter
public class FeignPlatformWithdrawConfigSaveReq {

    /**
     * 主键
     */
    private String id;
    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 有效时间内（单位s秒）
     */
    private Integer validTime;

    /**
     * 提款限额-最小金额
     */
    private BigDecimal minMoney;
    /**
     * 提款限额-最大金额
     */
    private BigDecimal maxMoney;

    /**
     * 参数值
     */
    private BigDecimal feeValue;
    /**
     * 手续费方式（1、百分比 2、固定值）
     */
    private Integer feeType;

    /**
     * 是否显示【0：默认不显示；1：显示】
     */
    private Boolean display;
    /**
     * 排序权重【从大到小】
     */
    private Integer idx;
}

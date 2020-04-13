package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author: liyunhua
 * @Date: 2019/4/3
 */
@Getter
@Setter
public class LegalCoinResp {

    /**
     * 表主键ID
     */
    private String id;


    /**
     * 法币币种名称
     */
    private String legalCoinName;

    /**
     * 法币图标url
     */
    private String icoUrl;

    /**
     * 法币图标url
     */
    private String icoUrlView;

    /**
     * 关联币种ID
     */
    private String relationCoinId;

    /**
     * 关联币种名称
     */
    private String relationCoinName;

    /**
     * CNY汇率
     */
    private BigDecimal toCny;

    /**
     * USDX汇率
     */
    private BigDecimal toUsdx;

    /**
     * 自定义汇率（默认通过定时器向托管平台同步汇率）【0否，1是】
     */
    private Boolean customizedRate;

    /**
     * 自定义汇率【0否，1是】
     */
    private String customizedRateDesc;

    /**
     * 币种描述
     */
    private String  description;

}

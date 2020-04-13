package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 * 支付方式/返利保存参数封装
 *
 * @author CaiRH
 * @since 2019-05-28
 */
@Getter
@Setter
public class FeignOffsiteExchangeRebateSaveReq {

    private String id;

    /**
     * 支付方式【1：现金支付。@EnumOffsiteExchangePayType】
     */
    private Integer payType;
    /**
     * 返利比例e.g. 0.05（百分之五）
     */
    private BigDecimal rebateRate;

    /**
     * 邀请返利比例
     */
    private BigDecimal inviteRebateRate;
    
    /**
     * 支付方式logo地址
     */
    private String icoUrl;

    /**
     * 展示二维码
     */
    private Boolean showQrCode;
    
    private Integer sort;
}

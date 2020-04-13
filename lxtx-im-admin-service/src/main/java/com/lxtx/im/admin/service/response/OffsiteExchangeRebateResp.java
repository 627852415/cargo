package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 * 支付方式/返利
 *
 * @author CaiRH
 * @since 2019-05-28
 */
@Getter
@Setter
public class OffsiteExchangeRebateResp {

    private String id;

    /**
     * 支付方式【1：现金支付。@EnumOffsiteExchangePayType】
     */
    private Integer payType;
    /**
     * 商家返利比例e.g. 0.05（百分之五）
     */
    private BigDecimal rebateRate;

    /**
     * 邀请返利比例
     */
    private BigDecimal inviteRebateRate;

    /**
     * 支付方式名称
     */
    private String payTypeName;

    /**
     * 展示二维码
     */
    private Boolean showQrCode;
    
    private Integer sort;

}

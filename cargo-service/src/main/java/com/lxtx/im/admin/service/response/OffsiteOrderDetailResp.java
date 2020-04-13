package com.lxtx.im.admin.service.response;

import lombok.Data;

@Data
public class OffsiteOrderDetailResp {
    private String goodsId;

    private String orderId;
    /**
     * 目标金额
     */
    private String targetAmount;

    /**
     * 目标币种id
     */
    private String targetCurrency;

    /**
     * 换汇金额
     */
    private String sourceAmount;

    /**
     * 换汇币种id
     */
    private String sourceCurrency;

    /**
     * 下单时的汇率
     */
    private String exchangeRate;

    private String exchangeRateStr;

    /**
     * 支付状态 1:未付款 2:已完成 3:已取消
     */
    private Integer status;

    private String statusStr;
}
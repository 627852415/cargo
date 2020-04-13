package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description:  后台线下订单分页返回
 * @author:   CXM
 * @create:   2019-04-22 17:00
 */
@Setter
@Getter
public class OffsiteExchangeOrderListPageResp{

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    @JsonIgnore
    private List<String> accountIdList;

    List<OffsiteExchangeOrderDto> records;

    @Getter
    @Setter
    public static class OffsiteExchangeOrderDto{

        /**
         * 订单Id
         */
        private String orderId;

        /**
         * 买家Id
         */
        private String buyerAccountId;

        /**
         * 买家电话
         */
        private String buyerTelephone;

        /**
         * 买家昵称
         */
        private String buyerName;

        /**
         * 商家Id
         */
        private String merchantAccountId;

        /**
         * 商家电话
         */
        private String merchantTelephone;

        /**
         * 商家昵称
         */
        private String merchantName;

        /**
         * 交易量
         */
        private BigDecimal targetAmount;

        /**
         * 交易单位
         */
        private String targetCurrency;

        /**
         * 交易额
         */
        private BigDecimal sourceAmount;

        /**
         * 交易单位
         */
        private String sourceCurrency;

        /**
         * 交易时的汇率
         */
        private BigDecimal exchangeRate;

        /**
         * 商家返利
         */
        private BigDecimal merchantRebateAmount;

        /**
         * 商家返利单位
         */
        private String merchantRebateAmountCurrency;

        /**
         * 平台返利
         */
        private BigDecimal platformRebateAmount;

        /**
         * 平台返利单位
         */
        private String platformRebateAmountCurrency;

        /**
         * 状态
         */
        private Integer status;

        /**
         * 商品类型: 0:无保证金 1:有保证金
         */
        private Integer goodsType;

        /**
         * 创建日期
         */
        private Date createTime;

        /**
         * 完成日期
         */
        private Date operateTime;
        
        private String paymentMethod;

        private String receiveMethod;
        /**
         * 邀请返利
         */
        @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
        private BigDecimal inviteRebateAmount;
        /**
         * 已返利（总）
         */
        @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
        private BigDecimal totalRebateAmount;
    }

}
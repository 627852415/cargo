package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 **/
@Getter
@Setter
public class OffsiteExchangeGoodsListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<OffsiteExchangeGoodsStatisticsDTO> records;

    @Getter
    @Setter
    public static class OffsiteExchangeGoodsStatisticsDTO {

        @JsonIgnore
        private String userId;

        /**
         * 用户Id
         */
        private String account;
        /**
         * 昵称
         */
        private String name;
        /**
         * 商品类型: 0:无保证金 1:有保证金
         */
        private Integer goodsType;
        /**
         * 手机号码
         */
        private String telephone;

        /**
         * 买家【我有】币种Id
         */
        @JsonIgnore
        private String sourceCoinId;

        /**
         * 买家【可买】币种Id
         */
        @JsonIgnore
        private String targetCoinId;

        /**
         * 买家【我有】币种
         */
        private String sourceCoin;

        /**
         * 买家【可买】币种
         */
        private String targetCoin;


        /**
         * 上架数量
         */
        private Integer pullOnVolume;

        /**
         * 上架数量
         */
        private Integer pullOffVolume;

        /**
         * 上架数量
         */
        private Integer pullCloseVolume;

        /**
         * 交易量
         */
        @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
        private BigDecimal completedVolume = BigDecimal.ZERO;

        /**
         * 交易额
         */
        @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
        private BigDecimal completedAmount = BigDecimal.ZERO;

        /**
         * 平均汇率
         */
        private BigDecimal avgExchangeRate = BigDecimal.ZERO;

        /**
         * 波动汇率百分比
         */
        private BigDecimal floatScopePercent = BigDecimal.ZERO;

        /**
         * 统计时间
         */
        private String statisticsTime ;
        /**
         * 返利
         */
        private BigDecimal merchantRebateAmount = BigDecimal.ZERO;
        /**
         * 利润
         */
        private BigDecimal platformRebateAmount = BigDecimal.ZERO;

    }

}

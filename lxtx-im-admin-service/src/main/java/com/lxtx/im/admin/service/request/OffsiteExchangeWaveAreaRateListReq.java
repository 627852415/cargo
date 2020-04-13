package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @since 2019-06-03
 */
@Getter
@Setter
public class OffsiteExchangeWaveAreaRateListReq {

    /**
     * 浮动汇率表id
     */
    @NotNull(message = "waveRateId不能为空")
    private String waveRateId;
    /**
     * 默认浮动汇率
     */
    private BigDecimal waveRate;
    /**
     * 兑换的币种名称
     */
    private String exchangeCoinName;
    /**
     * 支付的币种名称
     */
    private String payCoinName;

    private String targetCoinId;
    private String sourceCoinId;

    /**
     * 汇率显示模式
     */
    private Integer exchangeRateModel;

}

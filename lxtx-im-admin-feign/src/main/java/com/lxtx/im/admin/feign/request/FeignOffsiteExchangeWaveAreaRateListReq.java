package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @since 2019-06-03
 */
@Getter
@Setter
public class FeignOffsiteExchangeWaveAreaRateListReq {

    /**
     * 浮动汇率表id
     */
    @NotNull(message = "waveRateId不能为空")
    private String waveRateId;


}

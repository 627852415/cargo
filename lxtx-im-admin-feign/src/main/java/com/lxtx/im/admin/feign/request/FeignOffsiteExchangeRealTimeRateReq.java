package com.lxtx.im.admin.feign.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @since 2019-06-03
 */
@AllArgsConstructor
@Getter
@Setter
public class FeignOffsiteExchangeRealTimeRateReq {

    private String cuerrencyIn;
    private String cuerrencyOut;

}

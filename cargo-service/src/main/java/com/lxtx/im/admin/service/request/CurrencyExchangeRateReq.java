package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author CaiRH
 * @since 2019-06-17
 */
@Getter
@Setter
public class CurrencyExchangeRateReq {

    private String sourceCoin;
    private String targetCoin;


}

package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 换汇汇率请求支付方式feign
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-10-28
 **/
@Getter
@Setter
public class FeignOffsiteExchangeWaveRateRebateReq {

    /**
     * 换汇波动汇率表id
     */
    private String waveRateId;


}

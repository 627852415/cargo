package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author CaiRH
 * @since 2019-05-27 11:34
 */
@Getter
@Setter
public class FeignOffsiteExchangeWaveRateIdReq {

    /**
     * 表主键ID
     */
    @NotNull(message = "id不能为空")
    private String id;


}

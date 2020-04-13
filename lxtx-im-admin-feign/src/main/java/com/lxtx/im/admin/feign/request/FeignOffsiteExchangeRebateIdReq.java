package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author CaiRH
 * @since 2019-05-28
 */
@Getter
@Setter
public class FeignOffsiteExchangeRebateIdReq {

    /**
     * 表主键ID
     */
    @NotNull(message = "id不能为空")
    private String id;


}

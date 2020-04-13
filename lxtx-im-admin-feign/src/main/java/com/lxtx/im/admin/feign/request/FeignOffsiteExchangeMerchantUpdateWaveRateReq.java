package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 设置商家汇率
 */
@Setter
@Getter
public class FeignOffsiteExchangeMerchantUpdateWaveRateReq {

    /**
     * 商家id
     */
    @NotBlank(message = "商家id不能为空")
    private String id;

    /**
     *
     */
    @NotNull(message = "商家汇率不能为空")
    private BigDecimal merchantWaveRate;

}

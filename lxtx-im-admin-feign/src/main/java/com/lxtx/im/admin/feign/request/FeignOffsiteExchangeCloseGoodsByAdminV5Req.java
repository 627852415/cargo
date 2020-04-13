package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class FeignOffsiteExchangeCloseGoodsByAdminV5Req {

    /**
     * 挂单编号
     */
    @NotBlank(message = "挂单编号不能为空")
    private String goodsId;
}

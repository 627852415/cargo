package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class OffsiteExchangeOrderThawBuyerMargin {

    @NotBlank(message = "orderId 不能为空")
    private String orderId;

}

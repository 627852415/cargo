package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  结束交易
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class OffisteExchangeOrderEndReq {
    /**
     * 订单ID
     */
    @NotBlank(message = "orderId不能为空")
    private String orderId;
}

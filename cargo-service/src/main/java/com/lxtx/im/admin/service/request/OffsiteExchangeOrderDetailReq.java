package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
* @description:  线下汇换订单详情
* @author:   CXM
* @create:   2019-04-19 9:55
*/
@Getter
@Setter
public class OffsiteExchangeOrderDetailReq {
    /**
     * 订单编号
     */
    @NotBlank(message = "orderId不能为空")
    private String orderId;
}

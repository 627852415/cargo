package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author CaiRH
 * @since 2019-01-15
 */
@Getter
@Setter
public class FeignFastExchangeNoticeReq {

    /**
     * 用户编号
     */
    private String userId;
    /**
     * 订单编号-第三方平台（资金托管生成的订单号）
     */
    private String orderNo;
    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 通知ID，本地存储，用于通知重发用
     */
    private String notificationId;
}
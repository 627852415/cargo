package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * OTC订单变动通知
 *
 * @author Czh
 * Date: 2018/10/25 上午9:59
 */
@Getter
@Setter
public class FeignOtcNoticeReq {

    /**
     * 用户编号
     */
    private String userId;
    /**
     * 订单编号 orderNum
     */
    private String orderId;
    /**
     * 1：订单存在，0：订单不存在
     */
    private Integer state;

    /**
     * 通知ID，本地存储，用于通知重发用
     */
    private String notificationId;
}
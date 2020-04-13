package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 资金变动通知请求参数
 *
 * @author CaiRH
 * @since 2018-08-16
 **/
@Getter
@Setter
public class FeignSixMerNoticeReq {
    /**
     * 用户类型：【1:用户  2：商户】
     */
    private Integer type;
    /**
     * 6X 用户ID
     */
    private Integer userId;
    /**
     * 商户业务平台流水号
     */
    private String transferNum;
    /**
     * 托管平台流水编号
     */
    private String transferId;
    /**
     * 通知状态：【1:待处理 2:成功 3:失败】
     */
    private Integer state;

    /**
     * 通知ID，本地存储，用于通知重发用
     */
    private String notificationId;
}

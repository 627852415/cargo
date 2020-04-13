package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 通知重发处理
 *
 * @author CaiRH
 * @since 2019-06-12
 */
@Getter
@Setter
public class NotificationReissueHandleReq {

    /**
     * 通知ID
     */
    @NotBlank(message = "通知ID不能为空")
    private String notificationId;

    /**
     * 参数
     */
    @NotBlank(message = "请求参数不能为空")
    private String params;

    /**
     * 类型
     */
    @NotBlank(message = "通知类型不能为空")
    private Integer type;
}

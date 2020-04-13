package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 取消mq消息发送重试参数类
 *
 * @author CaiRH
 * @since 2020-01-03
 */
@Getter
@Setter
public class MqMsgSendCancelReq {

    /**
     * 表主键
     */
    @NotBlank(message = "消息id不能为空")
    private String id;

}

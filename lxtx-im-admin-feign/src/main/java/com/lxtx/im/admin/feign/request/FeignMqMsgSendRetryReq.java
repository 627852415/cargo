package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * mq消息发送重试参数类
 *
 * @author CaiRH
 * @since 2020-01-03
 */
@Getter
@Setter
public class FeignMqMsgSendRetryReq {

    /**
     * 表主键
     */
    private String id;

}

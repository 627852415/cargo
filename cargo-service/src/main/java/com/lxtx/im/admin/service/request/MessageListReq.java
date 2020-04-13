package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageListReq  extends BasePageReq {
    /**
     * 发送者
     */
    private String sender;

    /**
     *接受者
     */
    private String receiver;
    /**
     * 消息内型
     */
    private String action;
    /**
     *内容格式
     */
    private String format;

    private String status;
}

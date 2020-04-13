package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import static com.lxtx.framework.common.constants.Constants.MessageAction.ACTION_0;

@Getter
@Setter
public class FeignMessagePageReq extends BasePageReq {
    private String mid;
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

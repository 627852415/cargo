package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 给在线用户发送消息
 * @author: CXM
 * @create: 2018-09-06 14:22
 **/
@Setter
@Getter
public class  SendMessageReq {
    /**
     * 用户账号
     */
    private String receiver;
    /**
     * 发送内容
     */
    private String content;
}

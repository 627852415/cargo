package com.lxtx.im.admin.service.response;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 消息记录表
 * </p>
 *
 * @author liboyan
 * @since 2018-08-14
 */
@Getter
@Setter
public class MessageResp implements Serializable {


    private static final long serialVersionUID = 2270812010672799848L;
    private String mid;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息类型
     */

    private String action;

    private String extra;
    /**
     * 内容格式
     */
    private String format;
    /**
     * 消息接收者账号
     */
    private String receiver;
    /**
     * 消息发送者账号
     */
    private String sender;
    /**
     * 消息产生时间，13位时间戳
     */
    private Long timestamp;
    /**
     * 消息标题()
     */
    private String title;
    /**
     * 消息状态0: 未接收 1：已接收 2:已读
     */
    private String status;


}

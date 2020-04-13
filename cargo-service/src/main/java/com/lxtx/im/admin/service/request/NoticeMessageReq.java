package com.lxtx.im.admin.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author PengPai
 * Date: Created in 10:52 2020/3/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeMessageReq {

    //消息类型
    private String action;

    //消息发送者
    private String sender;

    //消息接收者
    private String receiver;

    //消息标题
    private String title;

    //消息内容
    private Map<String, Object> content;

    //消息扩展字段
    private Map<String, Object> extra;
}

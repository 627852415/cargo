package com.lxtx.im.admin.service.request;

import lombok.Data;

/**
* @description:  交易通知信息返回
* @author:   CXM
* @create:   2018-10-12 15:26
*/
@Data
public class NoticeInfoResp {
    /**
     * 短信通知
     */
    private String noticeMessage;
    /**
     * 邮箱通知
     */
    private String noticeEmail;
    /**
     * 电报通知
     */
    private String noticeTelegram;
    /**
     * 交易通知邮箱
     */
    private String adminMail;
    /**
     * 抄送人
     */
    private String ccMail;
    /**
     * 手机号码
     */
    private String telephone;
    /**
     * 电报机器人token
     */
    private String botToken;
    /**
     * 机器人群id
     */
    private String chatId;
    /**
     * 机器人群id
     */
    private String domain;

}

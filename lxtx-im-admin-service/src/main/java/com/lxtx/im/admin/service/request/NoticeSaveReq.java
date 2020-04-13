package com.lxtx.im.admin.service.request;

import lombok.Data;

/**
* @description:  币种交易监控通知保存入参
* @author:   CXM
* @create:   2018-10-12 15:26
*/
@Data
public class NoticeSaveReq {
    /**
     * 通知类型
     */
    private String noticeType;
    /**
     * 抄送邮箱
     */
    private String ccMail;
    /**
     * 邮箱
     */
    private String adminMail;
    /**
     * 电报机器人token
     */
    private String botToken;
    /**
     * 电报群id
     */
    private String chatId;
    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 所在域
     */
    private String domain;

}

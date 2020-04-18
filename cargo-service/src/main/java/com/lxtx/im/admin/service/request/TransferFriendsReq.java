package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author qing
 * @Description 好友转账入参数类
 * @date: 2019年11月21日 下午6:06:35
 */
@Getter
@Setter
public class TransferFriendsReq extends BasePageReq {

    //ID
    private String id;

    //转出用户钱包ID
    private String userId;

    //转出用户名称
    private String userName;

    //接收用户钱包ID
    private String receiverId;

    //转入用户名称
    private String receiverUserName;

    //交易时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    //交易时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    //币种
    private String coinId;

    //状态
    private Integer status;
    //转出用户国家简码
    private String countryCodeOut;
    //转出用户手机号
    private String telephoneOut;

    //转出用户国家简码
    private String countryCodeIn;
    //转出用户手机号
    private String telephoneIn;

    private boolean isKHShowAccount;
}

package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xumf
 * @date 2019/11/23 11:29
 */
@Setter
@Getter
public class AdminSendListReq extends BasePageReq {

    /**
     * 红包发送 ID
     */
    private String redPacketSendId;
    /**
     * 发送方 ID 或者昵称
     * 已废弃该字段，组合条件查询结果不对
     */
    private String userKeyword;
    
    private String userId;
    
    private String username;
    
    /**
     * 接收方用户ID 或者 群 ID 或者昵称
     */
    private String receiverKeyword;

    /**
     * 发送方手机号
     */
    private String senderTelephone;

    /**
     * 发送方国际简码
     */
    private String senderCountryCode;
    
    /**
     * 接收方手机号
     */
    private String receiverTelephone;

    /**
     * 接收方国际简码
     */
    private String receiverCountryCode;

    /**
     * 红包的类型，0:个人红包，1:群抢红包，2群人均红包
     */
    private Integer type;
    /**
     * 币种名称
     */
    private String coinId;
    /**
     * 0:未领取完，1：已领取完，2：未领取且已过期
     */
    private Integer state;
    /**
     * 开始时间-交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startCreateTime;
    /**
     * 结束时间-交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;
}

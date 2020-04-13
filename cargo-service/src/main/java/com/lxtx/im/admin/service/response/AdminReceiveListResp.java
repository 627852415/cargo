package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xumf
 * @date 2019/11/23 13:55
 */
@Data
public class AdminReceiveListResp {

    /**
     * 红包 ID
     */
    @ExcelField(name = "红包ID", orderBy = "1")
    private String redPacketId;
    /**
     * 接收者 ID
     */
    @ExcelField(name = "接收用户钱包ID", orderBy = "2")
    private String receiverId;
    /**
     * 接收者名称
     */
    @ExcelField(name = "接收用户昵称", orderBy = "3")
    private String receiverName;

    /**
     * 接受方手机号
     */
    @ExcelField(name = "接受方手机号", orderBy = "4")
    private String receiverCountryCode;

    /**
     * 接受方国际简码
     */
    @ExcelField(name = "接受方国际简码", orderBy = "5")
    private String receiverTelephone;

    /**
     * 红包的类型，0:个人红包，1:群抢红包，2群人均红包
     */
    private Integer type;
    /**
     * 红包的类型，0:个人红包，1:群抢红包，2群人均红包
     */
    @ExcelField(name = "类型", orderBy = "6")
    private String typeStr;
    /**
     * 发送方用户 ID
     */
    @ExcelField(name = "转出用户钱包ID", orderBy = "7")
    private String senderId;
    /**
     * 发送方用户昵称
     */
    @ExcelField(name = "转出用户昵称", orderBy = "8")
    private String senderName;

    /**
     * 发送方国际简码
     */
    @ExcelField(name = "发送方国际简码", orderBy = "9")
    private String senderCountryCode;

    /**
     * 发送方手机号
     */
    @ExcelField(name = "发送方手机号", orderBy = "10")
    private String senderTelephone;

    /**
     * 总金额
     */
    @ExcelField(name = "金额", orderBy = "11")
    @JsonSerialize(using = com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize.class)
    private BigDecimal amount;
    /**
     * 币种名称
     */
    @ExcelField(name = "币种", orderBy = "12")
    private String coinName;
    /**
     * 是否最佳手气
     */
    private Boolean lucky;
    /**
     * 是否最佳手气
     */
    @ExcelField(name = "是否最佳手气", orderBy = "13")
    private String luckyStr;
    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 交易时间
     */
    @ExcelField(name = "交易时间", orderBy = "14")
    private String createTimeStr;
    /**
     * 更新时间
     */
    @ExcelField(name = "更新时间", orderBy = "15")
    private String updateTimeStr;
}

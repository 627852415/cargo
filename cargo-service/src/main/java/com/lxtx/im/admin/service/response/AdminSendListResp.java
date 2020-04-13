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
public class AdminSendListResp {

    /**
     * 红包发送 ID
     */
    @ExcelField(name = "红包ID", orderBy = "1")
    private String id;
    /**
     * 发送方用户 ID
     */
    @ExcelField(name = "发送用户钱包ID", orderBy = "2")
    private String senderId;
    /**
     * 发送方用户昵称
     */
    @ExcelField(name = "发送用户昵称", orderBy = "3")
    private String senderName;

    /**
     * 发送方国际简码
     */
    @ExcelField(name = "发送方国际简码", orderBy = "4")
    private String senderCountryCode;

    /**
     * 发送方手机号
     */
    @ExcelField(name = "发送方手机号", orderBy = "5")
    private String senderTelephone;

    /**
     * 接收者 ID
     */
    @ExcelField(name = "接收用户钱包ID", orderBy = "6")
    private String receiverId;
    /**
     * 接收者名称
     */
    @ExcelField(name = "接收方昵称（用户/群）", orderBy = "7")
    private String receiverName;
    
    /**
     * 接受方国际简码
     */
    @ExcelField(name = "接受方国际简码", orderBy = "8")
    private String receiverTelephone;

    /**
     * 接受方手机号
     */
    @ExcelField(name = "接受方手机号", orderBy = "9")
    private String receiverCountryCode;

    /**
     * 0:未领取完，1：已领取完，2：未领取且已过期
     */
    private Integer state;
    /**
     * 0:未领取完，1：已领取完，2：未领取且已过期
     */
    @ExcelField(name = "状态", orderBy = "10")
    private String stateStr;
    /**
     * 红包的类型，0:个人红包，1:群抢红包，2群人均红包
     */
    private Integer type;
    /**
     * 红包的类型，0:个人红包，1:群抢红包，2群人均红包
     */
    @ExcelField(name = "类型", orderBy = "11")
    private String typeStr;
    /**
     * 总金额
     */
    @ExcelField(name = "总金额", orderBy = "12")
    @JsonSerialize(using = com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize.class)
    private BigDecimal amount;
    /**
     * 币种名称
     */
    @ExcelField(name = "币种", orderBy = "13")
    private String coinName;
    /**
     * 红包个数
     */
    @ExcelField(name = "红包个数", orderBy = "14")
    private Integer num;
    /**
     * 手续费
     */
    @ExcelField(name = "手续费", orderBy = "15")
    @JsonSerialize(using = com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize.class)
    private BigDecimal fee;
    /**
     * 红包标题
     */
    @ExcelField(name = "红包标题", orderBy = "16")
    private String title;
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
    @ExcelField(name = "交易时间", orderBy = "17")
    private String createTimeStr;
    /**
     * 更新时间
     */
    @ExcelField(name = "更新时间", orderBy = "18")
    private String updateTimeStr;
}

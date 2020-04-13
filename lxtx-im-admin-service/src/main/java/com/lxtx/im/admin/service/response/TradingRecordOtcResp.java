package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OTC充值记录查询
 *
 * @author xufeifei
 * @since 2019-11-23
 */
@Getter
@Setter
public class TradingRecordOtcResp {
    @ExcelField(name = "ID", orderBy = "1")
    private String id;

    /**
     * 内部订单号
     */
    @ExcelField(name = "内部订单号", orderBy = "2")
    private String orderId;

    /**
     *资金托管订单号
     */
    @ExcelField(name = "资金托管订单号", orderBy = "3")
    private String orderNum;

    /**
     * 用户钱包ID
     */
    @ExcelField(name = "用户钱包ID", orderBy = "4")
    private String userId;

    /**
     * 用户昵称
     */
    @ExcelField(name = "用户昵称", orderBy = "5")
    private String userName;
    /**
     * 国际区号
     */
    @ExcelField(name = "国际区号", orderBy = "6")
    private String countryCode;
    /**
     * 手机号
     */
    @ExcelField(name = "手机号", orderBy = "7")
    private String telephone;
    /**
     * 到账地址
     */
    @ExcelField(name = "到账地址", orderBy = "8")
    private String assignAddr;

    /**
     *  金额
     */
    @ExcelField(name = "金额", orderBy = "9")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal coinAmount;

    /**
     * 币种
     */
    @ExcelField(name = "币种", orderBy = "10")
    private String coinName;

    /**
     * 状态
     */
    //@ExcelField(name = "状态", orderBy = "9")
    private Integer state1;
    
    /**
     * 状态
     */
    @ExcelField(name = "状态", orderBy = "11")
    private String state1Value;
    
    /**
     * 提现方式
     */
    private Integer withdrawMethod;
    
    /**
     * 提现方式
     */
    @ExcelField(name = "提现方式", orderBy = "12")
    private String withdrawMethodValue;

    /**
     *  交易时间
     */
    @ExcelField(name = "交易时间", orderBy = "13")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     *  更新时间
     */
    @ExcelField(name = "更新时间", orderBy = "14")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     *  备注
     */
    @ExcelField(name = "备注", orderBy = "15")
    private String remarks;


}

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
 * OTC提现记录查询
 *
 * @author xufeifei
 * @since 2019-11-23
 */
@Getter
@Setter
public class TradingRecordOtcWithdrawResp {
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
     *  提取数量
     */
    @ExcelField(name = "提取数量", orderBy = "9")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal coinAmount;

    /**
     *  到账金额
     */
    @ExcelField(name = "到账金额", orderBy = "10")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal legalAmount;

    /**
     *  平台手续费
     */
    @ExcelField(name = "平台手续费", orderBy = "11")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal fee;

    /**
     *  价格
     */
    @ExcelField(name = "价格", orderBy = "12")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal price;

    /**
     * 币种
     */
    @ExcelField(name = "币种", orderBy = "13")
    private String coinName;

    /**
     * 状态
     */
    private Integer state1;
    
    /**
     * 状态
     */
    @ExcelField(name = "状态", orderBy = "14")
    private String state1Value;

    /**
     * 类型
     */
//    @ExcelField(name = "类型", orderBy = "13")
    private String type;
    
    /**
     * 提现方式
     */
    private Integer withdrawMethod;
    
    /**
     * 提现方式
     */
    @ExcelField(name = "提现方式", orderBy = "15")
    private String withdrawMethodValue;


    /**
     * 银行卡姓名
     */
    @ExcelField(name = "银行卡姓名", orderBy = "16")
    private String realname;


    /**
     * 银行
     */
    @ExcelField(name = "银行", orderBy = "17")
    private String bank;


    /**
     * 支行
     */
    @ExcelField(name = "支行", orderBy = "18")
    private String subbranch;

    /**
     * 银行卡号
     */
    @ExcelField(name = "银行卡号", orderBy = "19")
    private String account;


    /**
     *  交易时间
     */
    @ExcelField(name = "交易时间", orderBy = "20")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     *  更新时间
     */
    @ExcelField(name = "更新时间", orderBy = "21")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     *  备注
     */
    @ExcelField(name = "备注", orderBy = "22")
    private String remarks;
}
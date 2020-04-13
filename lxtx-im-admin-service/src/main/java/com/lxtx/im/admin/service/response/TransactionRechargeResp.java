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
 * 资金入账交易记录返回信息
 *
 * @author CaiRH
 * @since 2019-11-22
 */
@Getter
@Setter
public class TransactionRechargeResp {

    /**
     * 交易流水ID
     */
    @ExcelField(name = "ID", orderBy = "1")
    private String id;
    /**
     * 钱包用户ID
     */
    @ExcelField(name = "钱包用户ID", orderBy = "2")
    private String walletUserId;
    /**
     * 平台用户ID
     */
    private String platformUserId;
    /**
     * 用户昵称
     */
    @ExcelField(name = "用户昵称", orderBy = "3")
    private String username;
    
    @ExcelField(name = "国家简码", orderBy = "4")
    private String countryCode;
    
    /**
     * 电话号码
     */
    @ExcelField(name = "电话号码", orderBy = "5")
    private String telephone;
    
    /**
     * 类型【1：站内提币，2：链上充值，3：OTC充值】
     */
    private Integer type;
    /**
     * 类型名称
     */
    @ExcelField(name = "类型", orderBy = "6")
    private String typeName;
    /**
     * 转出地址
     */
    @ExcelField(name = "转出地址", orderBy = "7")
    private String fromAddr;
    /**
     * 到账地址
     */
    @ExcelField(name = "到账地址", orderBy = "8")
    private String toAddr;
    /**
     * 金额
     */
    @ExcelField(name = "金额", orderBy = "9")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal amount;
    /**
     * 币种
     */
    @ExcelField(name = "币种", orderBy = "10")
    private String coinName;
    /**
     * 外部交易编号
     */
    @ExcelField(name = "外部交易编号", orderBy = "11")
    private String thirdPartyTransferNum;
    /**
     * 外部订单ID
     */
    @ExcelField(name = "外部订单ID", orderBy = "12")
    private String thirdPartyOrderId;
    /**
     * 创建时间
     */
    @ExcelField(name = "交易时间", orderBy = "13")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelField(name = "更新时间", orderBy = "14")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    /**
     * 备注
     */
    @ExcelField(name = "备注", orderBy = "15")
    private String remarks;
    
}

package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-12-17 18:09
 * @Description
 */
@Data
public class RechargeTopgateOrderResp {
    /**
     * 平台账号
     */
    private String PlatformUserId;

    @ExcelField(name = "订单号", orderBy = "1")
    private String id;
    /**
     * Topgate订单ID
     */
    @ExcelField(name = "topgate订单号", orderBy = "2")
    private String thirdPartyOrderNo;

    /**
     * 钱包用户ID
     */
    @ExcelField(name = "钱包用户ID", orderBy = "3")
    private String userId;

    @ExcelField(name = "用户昵称", orderBy = "4")
    private String userName;

    /**
     * 国家简码
     */
    @ExcelField(name = "国家简码", orderBy = "5")
    private String countryCode;

    /**
     * 电话号码
     */
    @ExcelField(name = "电话号码", orderBy = "6")
    private String telephone;


    @ExcelField(name = "支付方式", orderBy = "7")
    private String payWayName;

    /**
     * 订单状态【1-处理中 2-成功 3-失败】
     */
    private Integer status;
    @ExcelField(name = "状态", orderBy = "8")
    private String statusName;
    /**
     * 类型
     */
    private Integer type;
    private String typeName;

    /**
     * 币种主键ID
     */
    private String coinId;

    /**
     * Topgate的实际支付金额,订单实际支付的金额
     */
    @ExcelField(name = "支付金额", orderBy = "9")
    private BigDecimal actualAmount;

    /**
     * Topgate的订单金额 ,单位：元，两位小数,不能小于 1.0，具体金额上限和下限根据实际情况制定
     */
    @ExcelField(name = "到账金额", orderBy = "10")
    private BigDecimal amount;

    /**
     * 币种名称
     */
    @ExcelField(name = "币种", orderBy = "11")
    private String coinName;

    /**
     * 内部手续费
     */
    @ExcelField(name = "内部手续费", orderBy = "12")
    private BigDecimal innerFee;

    /**
     * Topgate的手续费
     */
    @ExcelField(name = "第三方手续费", orderBy = "13")
    private BigDecimal thirdFee;
    
    /**
     * 总手续费
     */
    @ExcelField(name = "总手续费", orderBy = "14")
    private BigDecimal fee;

    /**
     * 秘密商户入账
     */
    private BigDecimal imMerchantIn;

    /**
     * topgate商户入账
     */
    private BigDecimal topgateMerchantIn;
 

    /**
     * Topgate充值方式【1：支付宝,2：微信】
     */
    private Integer payWay;


    /**
     * URL扩展字段,附加数据，在查询和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    private String extra;

    /**
     * 后台通知回调,需要带上 http://或 https://
     */
    private String notifyUrl;


    @ExcelField(name = "交易时间", orderBy = "15")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date updateTime;

    public String remarks;
}

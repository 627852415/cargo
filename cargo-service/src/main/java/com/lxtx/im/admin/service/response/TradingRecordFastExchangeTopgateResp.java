package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Topgate闪兑订单返回信息
 *
 * @author hechizhi
 * @since 2019-11-23
 */
@Getter
@Setter
public class TradingRecordFastExchangeTopgateResp {

    @ExcelField(name = "订单号", orderBy = "1")
    private String id;

    /**
     * tgpay的订单编号
     */
    @ExcelField(name = "topgate订单号", orderBy = "2")
    private String thirdPartyOrderNo;

    /**
     * 钱包用户ID
     */
    @ExcelField(name = "钱包用户ID", orderBy = "3")
    private String userId;

    /**
     * 用户昵称
     */
    @ExcelField(name = "用户昵称", orderBy = "4")
    private String userName;

    /**
     * 国际简码
     */
    @ExcelField(name = "国家简码", orderBy = "5")
    private String countryCode;

    /**
     * 电话号码
     */
    @ExcelField(name = "电话号码", orderBy = "6")
    private String telephone;

    /**
     * 订单的状态
     */
    @ExcelField(name = "状态", orderBy = "7")
    private String statusValue;

    /**
     * 支付的币种-币种ID
     */
    private String payCoinId;

    /**
     * 支付的币种-币种名称
     */
    @ExcelField(name = "支付币种", orderBy = "8")
    private String payCoinName;

    /**
     * 实际支付的数量
     */
    @ExcelField(name = "支付金额", orderBy = "9")
    private BigDecimal actualPayAmount;

    /**
     * 获得的币种-币种ID
     */
    private String gotCoinId;

    /**
     * 获得的币种-币种名称
     */
    @ExcelField(name = "获取币种", orderBy = "10")
    private String gotCoinName;

    /**
     * 实际获得的数量
     */
    @ExcelField(name = "获得金额", orderBy = "11")
    private BigDecimal actualGotAmount;

    /**
     * 实际的兑换率
     */
    @ExcelField(name = "实际兑换率", orderBy = "12")
    private BigDecimal actualRate;

    /**
     * 总的手续费
     */
    @ExcelField(name = "手续费", orderBy = "13")
    private BigDecimal fee;

    /**
     * 内部手续费
     */
    @ExcelField(name = "内部手续费", orderBy = "14")
    private BigDecimal innerFee;

    /**
     * tgpay手续费
     */
    @ExcelField(name = "外部手续费", orderBy = "15")
    private BigDecimal thirdFee;

    /**
     * 秘密商户入账
     */
    private BigDecimal imMerchantIn;

    /**
     * topgate商户入账
     */
    private BigDecimal topgateMerchantIn;

    /**
     * 秘密商户出账
     */
    private BigDecimal imMerchantOut;

    /**
     * topgate商户出账
     */
    private BigDecimal topgateMerchantOut;

    /**
     * 订单的状态【1：处理中 2：成功 3：失败】
     */
    private Integer status;



    /**
     * 商户收益，单位：gotCoin
     */
    @ExcelField(name = "商户收益", orderBy = "16")
    private BigDecimal profit;

    /**
     * 创建时间
     */
    @ExcelField(name = "交易时间", orderBy = "17")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date updateTime;

}
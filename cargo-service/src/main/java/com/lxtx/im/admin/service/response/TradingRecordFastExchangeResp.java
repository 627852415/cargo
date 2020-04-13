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
 * 闪兑交易流水返回信息
 *
 * @author xufeifei
 * @since 2019-11-23
 */
@Getter
@Setter
public class TradingRecordFastExchangeResp {

    @ExcelField(name = "ID", orderBy = "1")
    private String id;

    /**
     * 资金托管订单编号
     */
    @ExcelField(name = "资金托管订单编号", orderBy = "2")
    private String thirdPartyOrderNo;

    /**
     * 订单编号
     */
    @ExcelField(name = "订单编号", orderBy = "3")
    private String orderNo;

    /**
     * 钱包用户ID
     */
    @ExcelField(name = "钱包用户ID", orderBy = "4")
    private String userId;

    /**
     * 用户昵称
     */
    @ExcelField(name = "用户昵称", orderBy = "5")
    private String userName;

    /**
     * 订单的状态
     */
    //@ExcelField(name = "订单状态", orderBy = "6")
    private Integer status;
    
    /**
     * 订单的状态
     */
    @ExcelField(name = "状态", orderBy = "6")
    private String statusValue;

    /**
     * 支付的币种-币种名称
     */
    @ExcelField(name = "支付币种", orderBy = "7")
    private String payCoin;

    /**
     * 意向支付的数量
     */
    @ExcelField(name = "支付金额", orderBy = "8")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal payAmount;

    /**
     * 获得的币种-币种名称
     */
    @ExcelField(name = "获取币种", orderBy = "9")
    private String gotCoin;

    /**
     * 预期获得的数量
     */
    @ExcelField(name = "预期获得金额", orderBy = "10")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal expectGotAmount;

    /**
     * 手续费(最终完成状态下有值)，6x手续费
     */
    @ExcelField(name = "手续费", orderBy = "11")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal fee;

    /**
     * 资金托管闪兑手续费List<Map<String,Object>>
     */
//    @ExcelField(name = "手续费", orderBy = "11")
    private String serviceCharge;


    /**
     * 预期的兑换率
     */
    @ExcelField(name = "预期兑换率", orderBy = "12")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal expectRate;

    /**
     * 最低的兑换率
     */
    @ExcelField(name = "最低兑换率", orderBy = "13")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal floorRate;

    /**
     * 实际支付的数量(最终完成状态下有值)
     */
    @ExcelField(name = "实际支付金额", orderBy = "14")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal realPayAmount;

    /**
     * 实际获得的数量,已扣除手续费(最终完成状态下有值)
     */
    @ExcelField(name = "实际获取金额", orderBy = "15")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal realGotAmount;

    /**
     * 实际的兑换率(最终完成状态下有值)
     */
    @ExcelField(name = "实际兑换率", orderBy = "16")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal realRate;

    /**
     * 创建时间
     */
    @ExcelField(name = "交易时间", orderBy = "17")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date createTime;

    /**
     * 更新时间
     */
    @ExcelField(name = "更新时间", orderBy = "18")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date updateTime;

    /**
     * 手机号码
     * 2020-01-20
     */
    private String telephone;
    /**
     * 国际简码
     * 2020-01-20
     */
    private String countryCode;
}
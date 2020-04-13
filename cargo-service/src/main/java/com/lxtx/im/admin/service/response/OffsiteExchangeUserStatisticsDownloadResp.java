package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 用户换汇统计导出类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-16
 **/
@Getter
@Setter
public class OffsiteExchangeUserStatisticsDownloadResp {

    /**
     * 用户Id
     */
    @ExcelField(name = "用户Id", orderBy = "1")
    private String account;

    /**
     * 用户昵称
     */
    @ExcelField(name = "用户昵称", orderBy = "2")
    private String name;

    /**
     * 币对
     */
    @ExcelField(name = "币对", orderBy = "3")
    private String waveRate;

    /**
     * 订单完成数量
     */
    @ExcelField(name = "订单完成数量", orderBy = "4")
    private Integer orderCompletedVolume;

    /**
     * 订单取消数量
     */
    @ExcelField(name = "订单取消数量", orderBy = "5")
    private Integer orderCancelVolume;

    /**
     * 成交量
     */
    @ExcelField(name = "成交量", orderBy = "6")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal completedVolume;

    /**
     * 成交量单位
     */
    @ExcelField(name = "单位", orderBy = "7")
    private String completedVolumeCoinName;

    /**
     * 成交金额
     */
    @ExcelField(name = "成交金额", orderBy = "8")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal completedAmount;

    /**
     * 成交金额单位
     */
    @ExcelField(name = "单位", orderBy = "9")
    private String completedAmountCoinName;

    /**
     * 平均汇率
     */
    @ExcelField(name = "平均汇率", orderBy = "10")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal avgExchangeRate;

    /**
     * 平均波动汇率
     */
    @ExcelField(name = "波动汇率", orderBy = "11")
    private String floatScopePercent;

    /**
     * 统计时间
     */
    @ExcelField(name = "统计时间", orderBy = "12")
    private String statisticsTime;

}

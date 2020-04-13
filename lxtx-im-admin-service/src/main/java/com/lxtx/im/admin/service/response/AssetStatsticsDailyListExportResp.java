package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetStatsticsDailyListExportResp  {


    private String id;
    private String coinId;
    /**
     * 币种名称
     */
    @ExcelField(name = "币种名称", orderBy = "1")
    private String coinName;
    /**
     * 总资产
     */
    @ExcelField(name = "secret总资产(A=A1+A2+A3)", orderBy = "2")
    private BigDecimal totalAssets;
    /**
     * 可用资产
     */
    @ExcelField(name = "secret可用资产(A1)", orderBy = "3")
    private BigDecimal availableAssets;
    /**
     * 冻结资产
     */
    @ExcelField(name = "secret冻结资产(A2)", orderBy = "4")
    private BigDecimal frozenAssets;
    /**
     * 资金池资产
     */
    @ExcelField(name = "secret资金池资产(A3)", orderBy = "5")
    private BigDecimal poolAssetAmout;
    /**
     * topgate充值到账总资产
     */
    @ExcelField(name = "topgate充值到账资产(B)", orderBy = "6")
    private BigDecimal topgateRechargeAmount;
    /**
     * topgate提现支出总资产
     */
    @ExcelField(name = "topgate提现支出资产(C)", orderBy = "7")
    private BigDecimal topgateWithdrawAmount;
    /**
     * topgate闪兑支付总资产
     */
    @ExcelField(name = "topgate闪兑支付资产(D)", orderBy = "8")
    private BigDecimal topgateFastexchangePayAmount;
    /**
     * topgate闪兑获得总资产
     */
    @ExcelField(name = "topgate闪兑获得资产(E)", orderBy = "9")
    private BigDecimal topgateFastexchangeGotAmount;
    /**
     * 商户资产
     */
    @ExcelField(name = "资金托管资产(F)", orderBy = "10")
    private BigDecimal merchantAssets;
    /**
     * 盈亏
     */
    @ExcelField(name = "盈亏(F-(A-B+C+D-E))", orderBy = "11")
    private BigDecimal profitAndLoss;
    /**
     * 记录日期
     */
    private Date recordsDate;
    /**
     * 平台类型
     */
    private Integer platformType;


}

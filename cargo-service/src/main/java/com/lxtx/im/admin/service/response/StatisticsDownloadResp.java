package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 文件导出返回
 * @author: CXM
 * @create: 2018-09-11 15:12
 */
@Getter
@Setter
public class StatisticsDownloadResp implements Serializable {
    /**
     * 币种名称
     */
    @ExcelField(name = "币种名称", orderBy = "1")
    private String coinName;

    /**
     * 总资产
     */
    @ExcelField(name = "总资产", orderBy = "2")
    private String allAmount = "0.00000000";


    /**
     * 可用资产
     */
    @ExcelField(name = "可用资产", orderBy = "3")
    private String leftAmount = "0.00000000";

    /**
     * 冻结资产
     */
    @ExcelField(name = "冻结资产", orderBy = "4")
    private String frozenAmount = "0.00000000";

    /**
     * 资金池资产 A3
     */
    @ExcelField(name = "资金池资产", orderBy = "5")
    private String poolAssetAmout = "0.00000000";

    /**
     * 锁定资产
     */
//    @ExcelField(name = "锁定资产", orderBy = "5")
//    private String lockAmount = "0.00000000";


    /**
     * 转出资产
     */
//    @ExcelField(name = "转出资产", orderBy = "6")
//    private String outAmount = "0.00000000";


    /**
     * 转入资产
     */
//    @ExcelField(name = "转入资产", orderBy = "7")
//    private String enterAmount = "0.00000000";


    /**
     * 空投资产
     */
//    @ExcelField(name = "空投资产", orderBy = "8")
//    private String airdropAmount = "0.00000000";

    /**
     * topgate充值到账资产 B
     */
    @ExcelField(name = "topgate充值到账资产", orderBy = "6")
    private BigDecimal topgateRechargeAmount = BigDecimal.ZERO;

    /**
     * topgate提现支出资产 C
     */
    @ExcelField(name = "topgate提现支出资产", orderBy = "7")
    private BigDecimal topgateWithdrawAmount = BigDecimal.ZERO;

    /**
     * topgate闪兑支付资产 D
     */
    @ExcelField(name = "topgate闪兑支付资产", orderBy = "8")
    private BigDecimal topgateFastexchangePayAmount = BigDecimal.ZERO;

    /**
     * topgate闪兑获得资产 E
     */
    @ExcelField(name = "topgate闪兑获得资产", orderBy = "9")
    private BigDecimal topgateFastexchangeGotAmount = BigDecimal.ZERO;

    /**
     * 商户资产
     */
    @ExcelField(name = "商户资产", orderBy = "10")
    private String businessAmout = "0.00000000";

    /**
     * 盈亏
     */
    @ExcelField(name = "盈亏", orderBy = "11")
    private String gainOrLoss = "0.00000000";

}

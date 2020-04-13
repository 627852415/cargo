package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 手续费统计报表返回数据
 *
 * @author CaiRH
 * @since 2018-12-13
 **/
@Getter
@Setter
public class ChargeReportDataResp {

    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 币种名称
     */
    @ExcelField(name = "币种名称", orderBy = "1")
    private String coinName;

    /**
     * 统计日期
     */
    @ExcelField(name = "统计日期", orderBy = "2")
    private Date statisticalDate;

    /**
     * 红包手续费
     */
    @ExcelField(name = "红包手续费（内部）", orderBy = "3")
    private BigDecimal redEnvelopesFee;

    /**
     * 好友转账手续费
     */
    @ExcelField(name = "好友转账手续费（内部）", orderBy = "4")
    private BigDecimal friendTransferFee;

    /**
     * 牛牛游戏手续费
     */
    private BigDecimal gameNiuFee;

    /**
     * 游戏手续费
     */
    @ExcelField(name = "游戏手续费（内部）", orderBy = "5")
    private BigDecimal gameFee;
    /**
     * OTC提现手续费）
     */
    @ExcelField(name = "OTC提现手续费", orderBy = "6")
    private BigDecimal otc_withdraw_fee;

    /**
     * 钱包-内部手续费
     */
    @ExcelField(name = "钱包转账手续费（内部）", orderBy = "7")
    private BigDecimal walletInnerFee;

    /**
     * 钱包-第三方手续费（eg: 6X）
     */
    @ExcelField(name = "钱包转账手续费（6X）", orderBy = "8")
    private BigDecimal walletThreeFee;


    /**
     * 钱包转账手续费总计
     */
    private BigDecimal walletTransferFeeSum;

    /**
     * 所有手续费总计
     */
    @ExcelField(name = "总计", orderBy = "9")
    private BigDecimal allFeeSum;

}

package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author LiuLP on 2018/12/10.
 */
@Getter
@Setter
public class StatisticsFeeDownloadResp implements Serializable {
    /**
     * 币种名称
     */
    @ExcelField(name = "币种名称", orderBy = "1")
    private String coinName;

    /**
     * 红包手续费（内部）
     */
    @ExcelField(name = "红包手续费（内部）", orderBy = "2")
    private String redPacketAmount = "0.00000000";

    /**
     * 好友转账手续费（内部）
     */
    @ExcelField(name = "好友转账手续费(内部)", orderBy = "3")
    private String friendTransferAmount = "0.00000000";


    @ExcelField(name = "牛牛游戏手续费（内部）", orderBy = "4")
    private String niuNiuGameAmount = "0.00000000";
    /**
     * OTC提币手续费
     */
    @ExcelField(name = "OTC提币手续费", orderBy = "5")
    private String otcWithdrawFeeAmount = "0.00000000";

    /**
     * 钱包转账内部手续费
     */
    @ExcelField(name = "钱包转账手续费(内部)", orderBy = "6")
    private String walletInnerAmount = "0.00000000";

    /**
     * 钱包转账内部手续费
     */
    @ExcelField(name = "钱包转账手续费(6x)", orderBy = "7")
    private String walletThreeAmount = "0.00000000";

    /**
     * 钱包转账手续费(总计)
     */
    @ExcelField(name = "钱包转账手续费(总计)", orderBy = "8")
    private String walletAllAmount = "0.00000000";

    /**
     * 总手续费
     */
    @ExcelField(name = "总计", orderBy = "9")
    private String allAmount = "0.00000000";

}

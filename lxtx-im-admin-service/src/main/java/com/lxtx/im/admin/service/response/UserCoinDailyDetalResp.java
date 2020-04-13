package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class UserCoinDailyDetalResp {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 同步的记录的日期
     */
    @ExcelField(name = "快照日期", orderBy = "1")
    private Date recordsDate;

    /**
     * 用户ID
     */
    @ExcelField(name = "用户ID", orderBy = "2")
    private String userId;
    /**
     * 平台用户名
     */
    @ExcelField(name = "账户", orderBy = "3")
    private String accountName;

    /**
     * 币种名
     */
    @ExcelField(name = "币种", orderBy = "4")
    private String coinName;

    /**
     * 钱包地址
     */
    @ExcelField(name = "钱包地址", orderBy = "5")
    private String assignAddr;
    /**
     * 余额
     */
    @ExcelField(name = "账户余额", orderBy = "6")
    private BigDecimal leftAmount;
    /**
     * 冻结金额
     */
    @ExcelField(name = "冻结余额", orderBy = "7")
    private BigDecimal frozenAmount;

    /**
     * 锁定金额
     */
    @ExcelField(name = "锁定余额", orderBy = "8")
    private BigDecimal lockAmount;

    /**
     * user_coin表ID
     */
    private String userCoinId;

    /**
     * 币种ID
     */
    private String coinId;
}

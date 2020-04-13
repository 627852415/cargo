package com.lxtx.im.admin.service.response;

import com.baomidou.mybatisplus.annotations.TableId;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户游戏返点日结报表
 *
 * @since 2019-01-09
 */
@Getter
@Setter
@NoArgsConstructor
public class ReportRebateDailyResp {

    @TableId
    private String gid;

    /**
     * 钱包用户id
     */
    private String userId;

    /**
     * im账号
     */
    @ExcelField(name = "用户账号", orderBy = "3")
    private String account;

    /**
     * 游戏币种Id
     */
    private String gameCoinId;

    /**
     * 游戏币种名称
     */
    private String gameCoinName;

    /**
     * 报表时间(yyyy-MM-dd)
     */
    @ExcelField(name = "统计日期", orderBy = "1")
    private String reportDate;

    /**
     * 佣金/返点金额
     */
    private String rebateAmount;

    /**
     * 群主返点金额
     */
    @ExcelField(name = "群主返佣总额", orderBy = "5")
    private String ownerRebateAmount;

    /**
     * 下级返点金额
     */
    @ExcelField(name = "下级返佣总额", orderBy = "6")
    private String subordinateRebateAmount;

    @ExcelField(name = "用户名", orderBy = "2")
    private String userName;

    @ExcelField(name = "手机号", orderBy = "4")
    private String telephone;

}

package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import com.lxtx.im.admin.dao.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OTC买卖比值报表
 *
 * @Author: Czh
 * @Date: 2019/3/11
 */
@Getter
@Setter
public class OtcOrderDayStatisticsResp extends BaseModel {


    private String id;

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 钱包用户ID
     */
    @ExcelField(name = "用户ID", orderBy = "1")
    private String userId;

    /**
     * 用户名
     */
    @ExcelField(name = "用户名", orderBy = "2")
    private String name;;


    /**
     * 统计日期
     */
    private Date statisticalDate;

    /**
     * 当日交易金额
     */
    @ExcelField(name = "当日交易金额", orderBy = "3")
    private BigDecimal dayTotalAmount;

    /**
     * 当日OTC买入金额
     */
    @ExcelField(name = "当日OTC买入金额", orderBy = "4")
    private BigDecimal dayBuyAmount;

    /**
     * 当日OTC卖出金额
     */
    @ExcelField(name = "当日OTC卖出金额", orderBy = "5")
    private BigDecimal daySellAmount;

    /**
     * 当日比值
     */
    @ExcelField(name = "当日比值", orderBy = "6")
    private BigDecimal dayRatio;

    /**
     * 历史交易金额
     */
    @ExcelField(name = "历史交易金额", orderBy = "7")
    private BigDecimal historyTotalAmount;

    /**
     * 历史OTC买入金额
     */
    @ExcelField(name = "历史OTC买入金额", orderBy = "8")
    private BigDecimal historyBuyAmount;

    /**
     * 历史OTC卖出金额
     */
    @ExcelField(name = "历史OTC卖出金额", orderBy = "9")
    private BigDecimal historySellAmount;

    /**
     * 历史比值
     */
    @ExcelField(name = "历史比值", orderBy = "10")
    private BigDecimal historyRatio;

}

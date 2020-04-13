package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 17:36
 * @Description
 */
@Setter
@Getter
public class OffsiteExchangeGoodsDownloadResp {

    /**
     * 商品ID
     */
    @ExcelField(name = "商品ID", orderBy = "1")
    private String goodsId;

    /**
     * 商家ID
     */
    @ExcelField(name = "商家ID", orderBy = "2")
    private String merchantId;

    /**
     * 商家
     */
    @ExcelField(name = "商家", orderBy = "3")
    private String fullTelephone;

    /**
     * 总量
     */
    @ExcelField(name = "总量", orderBy = "4")
    private String totaltAmount;

    /**
     * 交易量
     */
    @ExcelField(name = "交易量", orderBy = "5")
    private String totaltSourceAmount;

    /**
     * 成交金额
     */
    @ExcelField(name = "成交量", orderBy = "6")
    private String transactionAmount;

    /**
     * 完成进度
     */
    @ExcelField(name = "完成进度", orderBy = "7")
    private String progressRate;

    /**
     *  限额范围
     */
    @ExcelField(name = "限额", orderBy = "8")
    private String limitRange;

    /**
     * 汇率
     */
    @ExcelField(name = "汇率", orderBy = "9")
    private BigDecimal exchangeRate;

    /**
     * 下架时间
     */
    @ExcelField(name = "下架时间", orderBy = "10")
    private Integer pullOffDay;

    /**
     * 创建时间
     */
    @ExcelField(name = "发布日期", orderBy = "11")
    public Date createTime;

    /**
     * 支付状态 1:未付款 2:已完成 3:已取消
     */
    @ExcelField(name = "状态", orderBy = "12")
    private String pullOffFlag;
}
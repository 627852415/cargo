package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
* @description:  系统提现申请返回
* @author:   CXM
* @create:   2018-12-19 16:47
*/
@Setter
@Getter
public class PlatformWithdrawApplyListResp {

    /**
     * 主键
     */
    @ExcelField(name = "流水号", orderBy = "1")
    private String id;
    /**
     * im用户账号
     */
    private String platformUserId;

    /**
     * 国际简码(CN/86)
     */
    @ExcelField(name = "国际简码", orderBy = "2")
    private String countryCode;

    /**
     * 手机号码
     */
    @ExcelField(name = "手机号码", orderBy = "3")
    private String telephone;

    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 币种名称
     */
    @ExcelField(name = "币种名称", orderBy = "4")
    private String coinName;
    /**
     * 申请提款数量
     */
    @ExcelField(name = "交易数量", orderBy = "5")
    private BigDecimal amount;

    /**
     * 交易总额
     */
    private BigDecimal totalMoney;

    /**
     * 交易总额(带‘￥’)
     */
    @ExcelField(name = "交易金额", orderBy = "6")
    private String totalMoneyStr;

    /**
     * 状态【1：待处理，2：处理中，3：已提交，4：成功，5:失败】
     */
    private Integer status;

    /**
     * 状态【1：待处理，2：处理中，3：已提交，4：成功，5:失败】
     */
    @ExcelField(name = "状态", orderBy = "7")
    private String statusMessage;
    /**
     * 当前单价/申请汇率
     */
    private BigDecimal currentPrice;
    /**
     * 当前单价/申请汇率(带‘￥’)
     */
    @ExcelField(name = "申请汇率", orderBy = "8")
    private String currentPriceStr;
    /**
     * 审核时单价/汇率
     */
    private BigDecimal auditedPrice;
    /**
     * 审核时单价/汇率(带‘￥’)
     */
    @ExcelField(name = "审核时汇率", orderBy = "9")
    private String auditedPriceStr;
    /**
     * 主行
     */
    @ExcelField(name = "申请银行", orderBy = "10")
    private String bank;
    /**
     * 银行卡号
     */
    @ExcelField(name = "银行账户", orderBy = "11")
    private String account;
    /**
     * 银行卡持有人姓名
     */
    @ExcelField(name = "账户姓名", orderBy = "12")
    private String realname;
    /**
     * 支行
     */
    @ExcelField(name = "所属支行", orderBy = "13")
    private String subbranch;
    /**
     * 创建时间
     */
    @ExcelField(name = "申请时间", orderBy = "14")
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelField(name = "处理时间", orderBy = "15")
    public Date updateTime;
}

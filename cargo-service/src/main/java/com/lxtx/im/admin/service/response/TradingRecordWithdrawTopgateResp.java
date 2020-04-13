package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Topgate闪兑交易流水返回信息
 *
 * @author hechizhi
 * @since 2019-11-23
 */
@Getter
@Setter
public class TradingRecordWithdrawTopgateResp {

    /**
     * 表主键ID
     */
    @ExcelField(name = "订单号", orderBy = "1")
    private String id;

    /**
     * 币支付订单ID
     */
    @ExcelField(name = "topgate订单号", orderBy = "2")
    private String topgateOrderId;

    /**
     * 订单状态【1-处理中 2-成功 3-失败】
     */
    private Integer status;

    /**
     * 钱包用户ID
     */
    @ExcelField(name = "钱包用户ID", orderBy = "3")
    private String userId;

    /**
     * 用户昵称
     */
    @ExcelField(name = "用户昵称", orderBy = "4")
    private String userName;

    /**
     * 国家简码
     */
    @ExcelField(name = "国家简码", orderBy = "5")
    private String countryCode;
    /**
     * 手机号码
     */
    @ExcelField(name = "手机号码", orderBy = "6")
    private String telephone;

    /**
     * 订单的状态
     */
    @ExcelField(name = "状态", orderBy = "7")
    private String statusValue;

    /**
     * 币支付提现方式【1：网银支付,2：微信扫码支付 3、支付宝扫码支付】
     */
    @ExcelField(name = "提现方式", orderBy = "8")
    private String payWayName;

    /**
     * 币种主键ID
     */
    private String coinId;

    /**
     * 币支付的提现货币类型,目前只支持 CNY
     */
    @ExcelField(name = "提现币种", orderBy = "9")
    private String coinName;

    /**
     * 币支付的订单金额
     */
    @ExcelField(name = "到账金额", orderBy = "10")
    private BigDecimal amount;

    /**
     * 币支付的实际支付金额,订单实际支付的金额(未扣手续费的金额)
     */
    @ExcelField(name = "支付金额", orderBy = "11")
    private BigDecimal actualAmount;

    /**
     * 总手续费费用
     */
    @ExcelField(name = "手续费", orderBy = "12")
    private BigDecimal fee;

    /**
     * 内部手续费费用
     */
    @ExcelField(name = "内部手续费", orderBy = "13")
    private BigDecimal innerFee;

    /**
     * 币支付的手续费费用
     */
    @ExcelField(name = "外部手续费", orderBy = "14")
    private BigDecimal thirdFee;

    /**
     * 秘密商户出账
     */
    @ExcelField(name = "秘密商户出账", orderBy = "15")
    private BigDecimal imMerchantOut;

    /**
     * topgate商户出账
     */
    @ExcelField(name = "topgate商户出账", orderBy = "16")
    private BigDecimal topgateMerchantOut;

    /**
     * 币支付提现方式【1：网银支付,2：微信扫码支付 3、支付宝扫码支付】
     */
    private Integer payWay;

    /**
     * URL扩展字段,附加数据，在查询和支付通知中原样返回
     * 该字段主要用于商户携带订单的自定义数据
     */
    private String extra;

    /**
     * 提现账户,payWay=AliPay时表示支付宝收款地址;
     * payWay=WechatPay时表示微信收款地址;
     * payWay=InternetBank表示银行卡号
     */
    @ExcelField(name = "提现账户", orderBy = "17")
    private String payAccount;

    /**
     * 持卡人姓名
     */
    @ExcelField(name = "持卡人姓名", orderBy = "18")
    private String realname;

    /**
     * 账号主从属,例如：蚂蚁金服/腾讯/招商银行
     */
    @ExcelField(name = "账号主从属", orderBy = "19")
    private String bank;

    /**
     * 账号子从属,例如：支付宝/微信支付/招商银行深圳盐田支行
     */
    @ExcelField(name = "账号子从属", orderBy = "20")
    private String subbranch;

    /**
     * 后台通知回调
     */
    @ExcelField(name = "回调url", orderBy = "21")
    private String notifyUrl;

    /**
     * 创建时间
     */
    @ExcelField(name = "创建时间", orderBy = "22")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date createTime;

    /**
     * 更新时间
     */
    @ExcelField(name = "更新时间", orderBy = "23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date updateTime;


}
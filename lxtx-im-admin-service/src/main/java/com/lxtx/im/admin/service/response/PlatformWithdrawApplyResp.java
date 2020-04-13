package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 平台提款申请/订单
 *
 * @author CaiRh
 * @since 2018-12-17
 */
@Setter
@Getter
public class PlatformWithdrawApplyResp{

    /**
     * 主键
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 国际简码(CN/86)
     */
    private String countryCode;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 币种名称
     */
    private String coinName;

    /**
     * 申请提款数量
     */
    private BigDecimal amount;
    /**
     * 币种手续费
     */
    private BigDecimal fee;

    /**
     * 当前单价/申请汇率
     */
    private BigDecimal currentPrice;
    /**
     * 审核时单价/汇率
     */
    private BigDecimal auditedPrice;


    /**
     * 交易总额
     */
    private BigDecimal totalMoney;

    /**
     * 状态【1：待处理，2：处理中，3：已提交，4：成功，5:失败】
     */
    private Integer status;

    /**
     * 状态值
     */
    private String statusValue;

    /**
     * 银行卡持有人姓名
     */
    private String realname;
    /**
     * 主行
     */
    private String bank;
    /**
     * 支行
     */
    private String subbranch;

    /**
     * 银行卡号
     */
    private String account;

    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 更新时间
     */
    public Date updateTime;

    /**
     * 创建时间
     */
    public String createFormatTime;

    /**
     * 更新时间
     */
    public String updateFormatTime;

    /**
     * 当前单价/申请汇率 > 审核时单价/汇率 为1
     */
    public int priceCompare;

}

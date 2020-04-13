package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 分佣订单表
 * </p>
 *
 * @author 
 * @since 2020-03-07
 */
@Setter
@Getter
public class CommissionOrderResp {

    /**
     * id
     */
    private String id;
    /**
     * 业务类型  1：撮合交易 2：理财宝 3：提现  4：支付
     * EnumCommissionOrderType
     */
    private Integer type;
    /**
     * 业务类型  1：撮合交易 2：理财宝 3：提现  4：支付
     * EnumCommissionOrderType
     */
    @ExcelField(name = "订单类型", orderBy = "5")
    private String orderType;
    /**
     * 业务表id
     */
    @ExcelField(name = "订单号", orderBy = "1")
    private String refId;
    /**
     * 返佣总金额
     */
    @ExcelField(name = "分佣金额", orderBy = "7")
    private BigDecimal totalAmount;
    /**
     * 订单手续费
     */
    private BigDecimal fee;
    /**
     * 币种主键ID
     */
    private String coinId;
    /**
     * 币种名称
     */
    @ExcelField(name = "币种", orderBy = "6")
    private String coinName;
    /**
     * 用户ID
     */
    @ExcelField(name = "用户ID", orderBy = "3")
    private String userId;
    /**
     * 用户名称
     */
    @ExcelField(name = "用户名称", orderBy = "2")
    private String userName;
    /**
     * 上级用户ID
     */
    @ExcelField(name = "上级用户ID", orderBy = "4")
    private String parentUserId;
    /**
     * 上级分佣
     */
    @ExcelField(name = "上级分佣", orderBy = "8")
    private BigDecimal parentCommissionAmount;
    /**
     * 平台利润
     */
    @ExcelField(name = "平台利润", orderBy = "9")
    private BigDecimal platformCommissionAmount;
    /**
     * 交易时间
     */
    @ExcelField(name = "交易时间", orderBy = "10")
    private String createTime;
}

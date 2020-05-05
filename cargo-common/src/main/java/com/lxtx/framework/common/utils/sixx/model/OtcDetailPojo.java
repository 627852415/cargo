package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

/**
 * otc获取订单详情
 *
 * @author CaiRH
 */
@Data
public class OtcDetailPojo {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单类型【1：买，2：卖】
     */
    private Integer type;
    /**
     * 币种
     */
    private String coin;
    /**
     * 交易虚拟币金额
     */
    private String coinAmount;
    /**
     * 交易法币金额
     */
    private String legalAmount;
    /**
     * 交易法币符号
     */
    private String legal;
    /**
     * 订单状态【0-初始创建 1-收款方未收款 2-收款方已收款（即订单完成）3-被投诉关闭 4-订单关闭】
     */
    private Integer state1;
    /**
     * 支付状态【1-未支付 2-已支付】
     */
    private Integer state2;
    /**
     * 交易创建时间
     */
    private String createTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 价格
     */
    private String price;
    /**
     * 跳转到OTC的url的token
     */
    private String token;

    /**
     * 业务平台订单id
     */
    private String orderNum;
}

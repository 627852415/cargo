package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 闪兑获取订单详情
 *
 * @author CaiRH
 */
@Data
public class FastExchangeDetailPojo {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 资金托管生成的订单编号
     */
    private String orderNo;
    /**
     * 币宝/商户平台生成的订单编号
     */
    private String orderNum;
    /**
     * 登录token
     */
    private String token;
    /**
     * 交易方式|1.限价
     */
    private Integer tradeWay;

    /**
     * 支付的币种
     */
    private String payCoin;
    /**
     * 意向支付的数量
     */
    private BigDecimal payAmount;
    /**
     * 获得的币种
     */
    private String gotCoin;

    /**
     * 预期的兑换率
     */
    private BigDecimal expectRate;
    /**
     * 最低的兑换率
     */
    private BigDecimal floorRate;
    /**
     * 预期获得的数量
     */
    private BigDecimal gotAmount;

    /**
     * 实际支付的数量(最终完成状态下有值)
     */
    private BigDecimal realPayAmount;
    /**
     * 实际获得的数量,已扣除手续费(最终完成状态下有值)
     */
    private BigDecimal realGotAmount;
    /**
     * 实际的兑换率(最终完成状态下有值)
     */
    private BigDecimal realRate;
    /**
     * 手续费(最终完成状态下有值)
     */
    private BigDecimal fee;
    /**
     * 订单的状态【1初始创建<br />0未成交<br/>1部分成交<br/>2已完成(最终完成状态)<br/>3部分成交已撤销(最终完成状态)<br/>4已撤销(最终完成状态)<br/>7任务处理中<br />9异常订单,需人工处理(最终状态)】
     */
    private Integer status;

    /**
     * 闪兑兑换开始时间
     */
    private Date startTime;

    /**
     * 成交均价
     */
    private BigDecimal processedPrice;

    /**
     * 资金托管闪兑手续费List<Map<String,Object>>
     */
    private String serviceCharge;
}

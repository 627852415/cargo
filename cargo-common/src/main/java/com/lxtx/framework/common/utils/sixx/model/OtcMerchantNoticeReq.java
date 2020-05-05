package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OTC卖单审核通知
 *
 * @author CaiRH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtcMerchantNoticeReq {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商户对订单的审核状态【1-审核通过,2-审核拒绝】
     */
    private Integer code;
    /**
     * 币宝生成的订单编号
     */
    private String orderNum;
    /**
     * 资金托管生成的订单编号
     */
    private String orderNo;
    /**
     * 币种
     */
    private String coin;
    /**
     * 卖/买币数量
     */
    private String coinAmount;
}
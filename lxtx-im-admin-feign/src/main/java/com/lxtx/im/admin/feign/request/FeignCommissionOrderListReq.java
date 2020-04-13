package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分佣订单表
 * </p>
 *
 * @author 
 * @since 2020-03-07
 */
@Getter
@Setter
public class FeignCommissionOrderListReq extends BasePageReq {


    /**
     * 业务类型  1：撮合交易 2：理财宝 3：提现  4：支付
     * EnumCommissionOrderType
     */
    private Integer type;

    /**
     * 用户ID（对该用户的直属上级返佣）
     */
    private String userId;
    /**
     * 订单号查询
     */
    private String refId;
    /**
     * 上级ID查询
     */
    private String parentId;
    /**
     * 币种名称查询
     */
    private String coinName;
    /**
     * 时间范围查询
     */
    private String searchTime;
}

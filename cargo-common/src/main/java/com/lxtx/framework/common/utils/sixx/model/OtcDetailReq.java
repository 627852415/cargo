package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * otc获取订单详情
 *
 * @author CaiRH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtcDetailReq {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单编号
     */
    private String orderId;

}
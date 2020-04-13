package com.lxtx.im.admin.service.response;

import lombok.Data;

import java.math.BigDecimal;

/**
* @description:  币种手续费
* @author:   CXM
* @create:   2018-10-12 15:26
*/
@Data
public class CoinChargeInfoResp {
    /**
     * 主键
     */
    private String id;
    /**
     * 手续费类型（1、红包； 2、好友转账； 3、钱包转账）
     */
    private int chargeType;
    /**
     * 币种名称
     */
    private String coinName;
    /**
     * 币种名称
     */
    private String coinId;
   /**
     * 参数值
     */
    private BigDecimal value;

    /**
     * 手续费方式（1、百分比 2、固定值）
     */
    private int type;
}

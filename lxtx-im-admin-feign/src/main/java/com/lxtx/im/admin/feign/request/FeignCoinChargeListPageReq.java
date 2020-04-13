package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 获取币种手续费列表入参
 * @author: tangdy
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class FeignCoinChargeListPageReq extends BasePageReq {

    /**
     * 手续费类型（1、红包； 2、好友转账； 3、钱包转账）
     */
    private int chargeType;
    /**
     * coinId
     */
    private String coinId;
}

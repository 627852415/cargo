package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 币种资产统计（含商户资产） feign远程调用类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-10-15
 **/
@Getter
@Setter
public class FeignStatisticsCoinContainMerchantAssetsReq {

    /**
     * 平台类型
     */
    private Integer platformType;

}

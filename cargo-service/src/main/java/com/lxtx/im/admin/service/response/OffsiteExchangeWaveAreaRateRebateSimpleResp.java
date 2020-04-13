package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 地区支付方式/返利 简单返回
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-10-25
 **/
@Getter
@Setter
public class OffsiteExchangeWaveAreaRateRebateSimpleResp {

    private String id;

    /**
     * 支付方式名称
     */
    private String payTypeName;

    /**
     * 选中标记【true：选中，false：不选中】
     */
    private Boolean chooseFlag = false;

}

package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @Description Topgate平台充值类型
 * @author qing 
 * @date: 2019年12月16日 上午9:04:09
 */
@Getter
@AllArgsConstructor
public enum EnumTopgateRechargeWay {

    WECHATPAY(1,"WechatPay","text.payway.name.wechat"),
    ALIPAY(2,"AliPay","text.payway.name.alipay");

    private Integer code;
    private String value;
    private String description;

    public void setValue(String value) {
        this.value = value;
    }

    public static EnumTopgateRechargeWay find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumTopgateRechargeWay element : EnumTopgateRechargeWay.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}


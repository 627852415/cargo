package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumTopgatePayWayType {
    //类型【1、微信扫码支付；2、支付宝扫码支付；3、银行卡；】
    //text.payway.name.wechat
    //text.payway.name.alipay
    //text.payway.name.bankcard

    BANK_CARD_PAYMENT(2, "银行卡","text.payway.name.bankcard"),
    WECHAT_PAYMENT(5, "微信","text.payway.name.wechat"),
    ALIPAY_PAYMENT(6, "支付宝","text.payway.name.alipay"),
    ;

    private Integer code;
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    private String key;

    public static EnumTopgatePayWayType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumTopgatePayWayType element : EnumTopgatePayWayType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumOffsiteExchangePayType {

	CASH_PAYMENT(1, "现金支付"),
    BANK_CARD_PAYMENT(2, "银行卡"),
    PAYPAL_PAYMENT(3, "paypal"),
    SECRET_PAYMENT(4, "fincy"),
    WECHAT_PAYMENT(5, "微信"),
    ALIPAY_PAYMENT(6, "支付宝"),
    HUIONE_PAYMENT(7, "Huione"),
    ;

    private Integer code;
    private String description;

    public static EnumOffsiteExchangePayType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumOffsiteExchangePayType element : EnumOffsiteExchangePayType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}
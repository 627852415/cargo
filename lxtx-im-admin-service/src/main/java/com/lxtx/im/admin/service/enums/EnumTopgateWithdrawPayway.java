package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @Description 币支付平台提现支付类型
 * @author xufeifei
 * @date: 2019/12/28
 */
@Getter
@AllArgsConstructor
public enum EnumTopgateWithdrawPayway {

    INTERNETBANK(1,"InternetBank","text.payway.name.bankcard"),
    WECHATPAY(2,"WechatPay","text.payway.name.wechat"),
    ALIPAY(3,"AliPay","text.payway.name.alipay");

    private Integer code;
    private String value;
    private String description;

    public void setValue(String value) {
        this.value = value;
    }

    public static EnumTopgateWithdrawPayway find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumTopgateWithdrawPayway element : EnumTopgateWithdrawPayway.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

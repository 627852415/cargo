package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 错误类型
 *
 * @author CaiRH
 */
@NoArgsConstructor
@AllArgsConstructor
public enum EnumExceptionRecordType {

    REGISTER(1, "注册6X用户"),
    WITHDRAW(2, "提现"),
    RECHARGE(3, "充币"),
    SIX_CALLBACK(4,"6X回调"),
    CASH_SWEEP(5,"资金归集"),
	CURRENCY(6, "获取6X币种"),
    COIN_ADDRESS(7,"获取币种的地址"),
    ACCURACY(8, "获取6X币种精确度")
    ,OTC_LOGIN(9, "登录OTC")
    ,OTC_ORDER(10, "获取订单详情")
    ,OTC_MERCHANT_NOTICE(11, "OTC卖单审核通知接口")
    ;

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EnumExceptionRecordType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumExceptionRecordType element : EnumExceptionRecordType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

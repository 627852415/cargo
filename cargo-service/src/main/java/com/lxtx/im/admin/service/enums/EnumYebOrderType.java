package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 * @author Lin hj
 * Date: 2019/4/2
 */
@Getter
@AllArgsConstructor
public enum EnumYebOrderType {


    TRANSFER_AMOUNT_IN_LEFT(1, "转入余额"),
    TRANSFER_AMOUNT_OUT_EARNINGS(2, "转出收益"),
    TRANSFER_AMOUNT_OUT_PRICIPAL(3, "转出本金"),
    TRANSFER_AMOUNT_TAKE_CHAIN(4, "提取链上资金");


    private Integer code;
    private String description;

    public static EnumYebOrderType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumYebOrderType element : EnumYebOrderType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

    public static String getDescription(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumYebOrderType element : EnumYebOrderType.values()) {
            if (element.getCode().equals(code)) {
                return element.getDescription();
            }
        }
        return null;
    }
}

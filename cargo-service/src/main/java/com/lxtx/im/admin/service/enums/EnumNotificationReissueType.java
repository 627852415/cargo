package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知类型
 *
 * @author CaiRH
 */
@AllArgsConstructor
@Getter
public enum EnumNotificationReissueType {

    SIX_CALLBACK(1, "6x资金回调"),
    OTC_CALLBACK(2, "OTC订单回调"),
    FEX_CALLBACK(3, "闪兑订单回调");

    private Integer code;
    private String description;

    public static EnumNotificationReissueType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumNotificationReissueType element : EnumNotificationReissueType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

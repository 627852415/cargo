package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商家状态【0：正常， 1：禁用】
 *
 * @author CaiRH
 */
@Getter
@AllArgsConstructor
public enum EnumPayMerchantStatus {

    NORMAL(0, "正常"),
    FORBIDDEN(1, "禁用");

    private Integer code;
    private String description;

    public static EnumPayMerchantStatus find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumPayMerchantStatus element : EnumPayMerchantStatus.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

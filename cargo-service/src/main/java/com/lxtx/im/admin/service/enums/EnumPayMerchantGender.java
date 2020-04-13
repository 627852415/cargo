package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: liyunhua
 * @Date: 2019/3/13
 */
@Getter
@AllArgsConstructor
public enum EnumPayMerchantGender {

    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOWN(2, "未知");

    private Integer code;
    private String description;

    public static EnumPayMerchantGender find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumPayMerchantGender element : EnumPayMerchantGender.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

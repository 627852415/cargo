package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumPlatformType {
    IM(1, "im"),
    SDK(2, "sdk");

    private Integer code;
    private String description;

    public static EnumPlatformType find(Integer code) {
        if (code == null) {
            return null;
        }
        for (EnumPlatformType enumPlatformType : EnumPlatformType.values()) {
            if (code.equals(enumPlatformType.code)) {
                return enumPlatformType;
            }
        }
        return null;
    }
}

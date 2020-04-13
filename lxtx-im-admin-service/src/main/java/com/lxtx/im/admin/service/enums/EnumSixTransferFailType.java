package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum EnumSixTransferFailType {

    /**
     * 站外提币
     */
    SIX_TRANSFER_FAIL_TYPE(1, "站外提币"),
    /**
     * 余额宝转入提币
     */
    YEB_TRANSFER_FAIL_TYPE(2, "余额宝转入提币");

     private Integer code;
    private String description;

    public static EnumSixTransferFailType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumSixTransferFailType element : EnumSixTransferFailType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

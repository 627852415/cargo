package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * usbToken状态值
 *
 * @author CaiRH
 */
@Getter
@AllArgsConstructor
public enum EnumUsbTokenVerifyStatus {

    NOT_FIND(404, "操作失败，失败原因：usbtoken签名过程无返回"),
    NOT_UNLOCK(500, "操作失败，没有USB token");

    private Integer code;
    private String description;

    public static EnumUsbTokenVerifyStatus find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumUsbTokenVerifyStatus element : EnumUsbTokenVerifyStatus.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

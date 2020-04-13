package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;

/**
 * <p>
 *     账号状态
 * </p>
 *
 * @author liboyan
 * @Date 2018-12-11 10:57
 * @Description
 */
@AllArgsConstructor
public enum EnumUserState {
    START_USING ("0", "启用"),
    FORBIDDEN("1", "禁用"),
    FREEZE("2", "冻结");

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EnumUserState find(String code) {
        if (code == null) {
            return null;
        }

        for (EnumUserState element : EnumUserState.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

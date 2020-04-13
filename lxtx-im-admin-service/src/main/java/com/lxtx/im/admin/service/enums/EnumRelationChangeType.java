package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;

/**
 * @Author: liyunhua
 * @Date: 2019/4/1
 */
@AllArgsConstructor
public enum EnumRelationChangeType {

    ALL_CHANGE_AND_ME(1, "转移包括本人及所有下级"), ALL_CHANGE_NOT_INCLUDED_ME(2, "转移所有下级，不包括本人");

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EnumRelationChangeType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumRelationChangeType element : EnumRelationChangeType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }


    public static String findDescription(Integer code) {
        for (EnumRelationChangeType gameType : EnumRelationChangeType.values()) {
            if (gameType.code.equals(code)) {
                return gameType.getDescription();
            }
        }
        return null;
    }
}
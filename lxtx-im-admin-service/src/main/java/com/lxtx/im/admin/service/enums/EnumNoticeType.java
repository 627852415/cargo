package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
* @description:  通知类型
* @author:   CXM
* @create:   2018-10-30 21:42
*/
@NoArgsConstructor
@AllArgsConstructor
public enum EnumNoticeType {

    MESSAGE("1", "短信"),
    TELEGRAM("2", "电报"),
    EMAIL("3", "邮件");

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EnumNoticeType find(String code) {
        if (code == null) {
            return null;
        }

        for (EnumNoticeType element : EnumNoticeType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

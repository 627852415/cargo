package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumPullOffFlag {

    PULL_OFF(0, "上架"),
    PUSH_ON(1, "下架"),
    PUSH_CLOSE(2, "关闭");

	private Integer code;
    private String description;

    public static EnumPullOffFlag find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumPullOffFlag element : EnumPullOffFlag.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
	
}

package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author PengPai
 * Date: Created in 15:22 2020/2/25
 */
@Getter
@AllArgsConstructor
public enum EnumNoticeBroadCastStatus {

    NO_PUBLISH(0, "未发布"),

    PUBLISHED(1, "已发布"),

    REPEAL(2, "已撤销");

    private Integer code;
    private String description;

    public static EnumNoticeBroadCastStatus find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumNoticeBroadCastStatus element : EnumNoticeBroadCastStatus.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author PengPai
 * Date: Created in 11:15 2020/2/26
 */
@Getter
@AllArgsConstructor
public enum EnumNoticeBroadCastType {

    IMMEDIATELY(0, "立即发布", "notice_broadcast"),
    TIMING(1, "定时发布", "notice_repeal");

    private Integer code;
    private String description;
    private String instanceId;

    public static EnumNoticeBroadCastType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumNoticeBroadCastType element : EnumNoticeBroadCastType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

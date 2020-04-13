package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 系统提现状态
 *
 * @author tangdy
 */
@NoArgsConstructor
@AllArgsConstructor
public enum EnumPlatformWithdrawApplyStatus {

    PENDING (3, "未处理"),
    SUCCESS(4, "已打款"),
    FAILED(5, "已驳回");

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EnumPlatformWithdrawApplyStatus find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumPlatformWithdrawApplyStatus element : EnumPlatformWithdrawApplyStatus.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

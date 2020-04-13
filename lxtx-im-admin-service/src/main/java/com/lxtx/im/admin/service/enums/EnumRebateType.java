package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-01-22 19:42
 * @Description
 */
@AllArgsConstructor
public enum EnumRebateType {


    GAME_REBATE(0, "游戏返佣"),

    OWNER_REBATE(1, "群主返佣"),

    SUBORDINATE_REBATE(2, "下级返佣");

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

package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;

/**
 * @author Czh
 * Date: 2018/10/24 下午6:42
 */
@AllArgsConstructor
public enum EnumGameType {

    NIU_NIU(1, "牛牛"), BLACKJACK(2, "21点"),MINESWEEPER(3,"扫雷");

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EnumGameType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumGameType element : EnumGameType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }


    public static String findDescription(Integer code) {
        for (EnumGameType gameType : EnumGameType.values()) {
            if (gameType.code.equals(code)) {
                return gameType.getDescription();
            }
        }
        return null;
    }
}
package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Czh
 * Date: 2018/8/20 下午3:54
 */
@AllArgsConstructor
@Getter
public enum EnumCapitalOperationRefType {

    WITHDRAW(1, "提币/转账"),

    RECHARGE(2, "充值/收款"),

    TRANSFER(3, "站内转账/好友转账"),

    AIRDROP(4, "空投"),

    SEND_RED_PACKET(5, "红包发送"),

    RECEIVE_RED_PACKET(6, "接收红包"),

    GAME_BANKER(7, "庄家"),

    GAME_PLAYER(8, "玩家"),

    THIRD_GAME_TRANSFER(9, "第三方游戏好友转账"),

    GAME_VERSION_NEW(10, "新版游戏（21点）"),

    PLATFORM_WITHDRAW(11, "平台提款"),


    OTC_WITHDRAW(12, "OTC提款"),


    OTC_RECHARGE(13, "OTC充值"),


    GAME_REBATE(14, "游戏分佣"),

    FEX_WITHDRAW(15, "闪兑"),

    OWNER_REBATE(16, "群主返佣"),

    SUBORDINATE_REBATE(17, "下级返佣"),
    ;

    private Integer code;
    private String description;

    public static EnumCapitalOperationRefType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumCapitalOperationRefType element : EnumCapitalOperationRefType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

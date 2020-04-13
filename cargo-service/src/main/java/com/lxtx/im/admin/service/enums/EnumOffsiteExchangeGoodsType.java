package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *商品类型: 0:无保证金 1:有保证金
 */
@Getter
@AllArgsConstructor
public enum EnumOffsiteExchangeGoodsType {

    NO_MARGIN(0, "无保证金"),
    MARGIN(1, "有保证金");

    private Integer code;
    private String description;

    public static EnumOffsiteExchangeGoodsType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumOffsiteExchangeGoodsType element : EnumOffsiteExchangeGoodsType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
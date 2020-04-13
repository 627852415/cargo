package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Czh
 * Date: 2018/8/17 下午4:05
 */
@Getter
@AllArgsConstructor
public enum EnumWithdrawApplyState {
    /**
     * 待处理
     */
    PENDING(1, "待处理"),
    /**
     * 处理中
     */
    HANDING(2, "处理中"),
    /**
     * 已提交
     */
    COMMITTED(3, "已提交"),
    /**
     * 成功
     */
    SUCCESS(4, "成功"),
    /**
     * 失败
     */
    FAILURE(5, "失败"),
    /**
     * 部分成功
     */
    PARTIAL_SUCCESS(6, "部分成功");

    private Integer code;
    private String description;

    public static EnumWithdrawApplyState find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumWithdrawApplyState element : EnumWithdrawApplyState.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

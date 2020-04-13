package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 错误类型
 *
 * @author tangdy
 */
@NoArgsConstructor
@AllArgsConstructor
public enum EnumExceptionRecordStatus {

    PENDING (0, "未处理"),
    IN_HAND(1, "处理中"),
    FAILED(2, "失败"),
    SUCCESS(3, "成功");

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EnumExceptionRecordStatus find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumExceptionRecordStatus element : EnumExceptionRecordStatus.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

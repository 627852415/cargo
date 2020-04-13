package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Czh
 * Date: 2019-04-24 15:35
 */
@AllArgsConstructor
@Getter
public enum EnumOffsiteExchangeComplaint {

    UNPROCESSED(0, "未处理"),
    PROCESSING(1, "处理中"),
    PROCESSING_CANCELLED(2, "处理完成"),
    CLOSE(3, "已关闭"),
    REVOCATION(4, "已撤销"),
    ;

    private Integer code;
    private String description;

    public static EnumOffsiteExchangeComplaint find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumOffsiteExchangeComplaint element : EnumOffsiteExchangeComplaint.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

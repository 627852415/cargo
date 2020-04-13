package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商家认证状态【1:未认证、2:待审核、3:已认证、4:已拒绝】
 *
 * @author CaiRH
 */
@Getter
@AllArgsConstructor
public enum EnumPayMerchantCertificateStatus {

    UN_CERTIFIED(1, "未认证"),
    TO_BE_AUDITED(2, "待审核"),
    CERTIFIED(3, "已认证"),
    REJECTED(4, "已拒绝"),
    /**
     * 为了方便页面传值，这里额外添加一个状态
     */
    FROZEN(0, "已冻结");

    private Integer code;
    private String description;

    public static EnumPayMerchantCertificateStatus find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumPayMerchantCertificateStatus element : EnumPayMerchantCertificateStatus.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

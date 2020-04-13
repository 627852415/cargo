package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author PengPai
 * Date: Created in 15:25 2020/3/25
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EnumCountryCodeType {

    KH("KH", "柬埔寨");

    private String code;
    private String country;

    public static EnumCountryCodeType find(String code) {
        if (code == null) {
            return null;
        }

        for (EnumCountryCodeType element : EnumCountryCodeType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}

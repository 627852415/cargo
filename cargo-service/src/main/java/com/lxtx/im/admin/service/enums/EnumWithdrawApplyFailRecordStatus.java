package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 提现申请失败记录状态【0：待处理，1：处理失败，2：处理成功】
 *
 * @author CaiRH
 */
@Getter
@AllArgsConstructor
public enum EnumWithdrawApplyFailRecordStatus {

    INIT(0, "待处理"),
    DEAL_FAIL(1, "处理失败"),
    DEAL_SUCCESS(2, "处理成功");

    private Integer code;
    private String description;

    public static EnumWithdrawApplyFailRecordStatus find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumWithdrawApplyFailRecordStatus element : EnumWithdrawApplyFailRecordStatus.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}

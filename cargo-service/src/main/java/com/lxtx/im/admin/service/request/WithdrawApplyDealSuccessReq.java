package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class WithdrawApplyDealSuccessReq {
    /**
     * 记录ID
     */
    @NotBlank(message = "记录ID不能为空")
    private String recordId;

    /**
     * 业务平台交易编号/业务平台内唯一编号
     */
    private String transferNum;
}

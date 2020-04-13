package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class FeignWithdrawApplyFailRecordDealFailReq {
    @NotBlank(message = "记录ID不能为空")
    private String recordId;

}

package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: OTC充值交易记录查询
 * @author: xufeifei
 * @date: 2019/11/24
 */
@Setter
@Getter
public class TradingRecordOtcWithdrawDetailReq {
    @NotBlank(message = "id不能为空")
    private String id;
}

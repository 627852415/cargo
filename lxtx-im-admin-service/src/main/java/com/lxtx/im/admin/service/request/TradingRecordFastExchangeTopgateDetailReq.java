package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: Topgate闪兑交易记录查询
 * @author: hechizhi
 * @date: 2020-1-3
 */
@Setter
@Getter
public class TradingRecordFastExchangeTopgateDetailReq {

    @NotBlank(message = "id不能为空")
    private String id;
}

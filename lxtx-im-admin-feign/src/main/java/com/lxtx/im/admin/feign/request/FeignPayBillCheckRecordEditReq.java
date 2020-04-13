package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @description: 查询对账列表
 * @author: Ppai
 * @create: 2019-03-12 13:50
 **/
@Getter
@Setter
public class FeignPayBillCheckRecordEditReq {
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    private String cycleId;

    private Integer cycle;

    private List<PayCoinRateResp> coinList;
}

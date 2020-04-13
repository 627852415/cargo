package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
* @description:  线下汇换投诉详情
* @author:   CXM
* @create:   2019-04-19 9:55
*/
@Getter
@Setter
public class OffsiteExchangeComplaintDetailReq {
    /**
     * 投诉id
     */
    @NotBlank(message = "投诉id不能为空")
    private String id;
}

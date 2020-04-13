package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  线下汇换投诉记录
* @author:   CXM
* @create:   2019-04-19 9:55
*/
@Getter
@Setter
public class FeignOffsiteExchangeComplaintRecordListReq {
    /**
     * 投诉id
     */
    private String id;
}

package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
* @description:  线下汇换投诉处理完成
* @author:   CXM
* @create:   2019-04-19 9:55
*/
@Getter
@Setter
public class FeignOffsiteExchangeComplaintCompletedReq {
    /**
     * 投诉id
     */
    private String arbitrationId;

    /**
     * 处理内容
     */
    private String content;

    /**
     * 操作人
     */
    private String createBy;

    /**
     * 1为买家胜诉，2为卖家胜诉
     *
     */
    @NotNull
    private Integer winner;
}

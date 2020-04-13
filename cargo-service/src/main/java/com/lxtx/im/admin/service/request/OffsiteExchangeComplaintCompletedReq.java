package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
* @description:  线下汇换投诉处理完成
* @author:   CXM
* @create:   2019-04-19 9:55
*/
@Getter
@Setter
public class OffsiteExchangeComplaintCompletedReq {
    /**
     * 投诉id
     */
    @NotBlank(message = "投诉id不能为空")
    private String arbitrationId;

    /**
     * 发送内容
     */
    @NotBlank(message = "发送内容不能为空")
    private String content;

    /**
     * 1为买家胜诉，2为卖家胜诉
     *
     */
    @NotNull
    private Integer winner;
}

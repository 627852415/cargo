package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
* @description:  线下汇换投诉记录保存
* @author:   CXM
* @create:   2019-04-19 9:55
*/
@Getter
@Setter
public class OffsiteExchangeComplaintRecordSaveReq {
    /**
     * 仲裁id
     */
    private String arbitrationId;

    /**
     * 处理凭证图片路径
     */
    private String images;

    /**
     * 处理内容
     */
    @NotBlank(message = "处理内容不能为空")
    private String content;
}

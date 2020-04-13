package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  线下汇换投诉记录保存
* @author:   CXM
* @create:   2019-04-19 9:55
*/
@Getter
@Setter
public class FeignOffsiteExchangeComplaintRecordSaveReq {
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
    private String content;

    /**
     * 处理人
     */
    private String createBy;
}

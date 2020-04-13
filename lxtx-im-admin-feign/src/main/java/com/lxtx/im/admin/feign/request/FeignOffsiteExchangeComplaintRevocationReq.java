package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 换汇撤销投诉请求feign类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-07
 **/
@Getter
@Setter
public class FeignOffsiteExchangeComplaintRevocationReq {

    /**
     * 投诉Id
     */
    private String complaintId;

    /**
     * 操作来源(0:系统，1：IM)
     */
    private Integer sourceType;

    /**
     * 操作人名
     */
    private String createBy;

}

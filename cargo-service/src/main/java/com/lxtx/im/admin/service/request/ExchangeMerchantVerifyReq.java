package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: liyunhua
 * @Date: 2019/4/23
 */
@Getter
@Setter
@ToString
public class ExchangeMerchantVerifyReq {

    /**
     * ID
     */
    private String id;

    /**
     * 拒绝理由
     */
    private String rejectReason;

    /**
     * 审核状态
     */
    private String certificateStatus;


}
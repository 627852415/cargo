package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
* @description:  商家列表
* @author:   CXM
* @create:   2019-03-11 16:30
*/
@Getter
@Setter
public class PayMerchantListPageReq extends BasePageReq {
    /**
     * 订单状态
     *
     */
    private String account;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    /**
     * 结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 认证状态
     */
    private Integer certificateStatus;

}

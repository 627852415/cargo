package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @description:  线下汇换订单下载
* @author:   CXM
* @create:   2019-04-22 13:48
*/
@Getter
@Setter
public class OffsiteExchangeOrderDownloadReq {

    /**
     * 买家账号
     */
    private String userId;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 价格范围开始
     */
    private String startPrice;
    /**
     * 价格范围结束
     */
    private String endPrice;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    /**
     * 支付状态 1:未付款 2:已完成 3:已取消
     */
    private Integer status;
}

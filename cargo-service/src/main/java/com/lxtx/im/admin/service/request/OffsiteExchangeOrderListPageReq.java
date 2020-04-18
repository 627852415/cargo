package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @description:  线下汇换订单分页
* @author:   CXM
* @create:   2019-04-22 13:48
*/
@Getter
@Setter
public class OffsiteExchangeOrderListPageReq extends BasePageReq {

    /**
     * 买家账号Id
     */
    private String buyerAccount;

    /**
     * 买家昵称
     */
    private String buyerNickName;

    /**
     * 买家电话号码
     */
    private String buyerTelephone;
    /**
     * 买家账号Id
     */
    private String merchantAccount;

    /**
     * 买家昵称
     */
    private String merchantNickName;

    /**
     * 买家电话号码
     */
    private String merchantTelephone;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 币对Id
     */
    private String waveRateId;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

}

package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
* @description:  线下汇换订单分页
* @author:   CXM
* @create:   2019-04-22 13:48
*/
@Getter
@Setter
public class FeignOffsiteExchangeOrderListPageReq extends BasePageReq {

    /**
     * 订单Id
     */
    private String orderId;
    /**
     * 账号Id
     */
    private List<String> accountList;
    /**
     * 商家账号
     */
    private List<String> merchantAccountList;

    /**
     * 币对Id
     */
    private String waveRateId;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 是否柬埔寨演示账号
     */
    private boolean isShowAccount;

}

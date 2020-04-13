package com.lxtx.im.admin.service.response;

import lombok.Data;

import java.util.Date;

/**
* @description:  线下汇换投诉列表返回
* @author:   CXM
* @create:   2019-04-25 10:25
*/
@Data
public class OffsiteExchangeArbitrationResp {

    /**
     *
     * 主键ID
     */
    private String id;
    /**
     * 换汇订单号
      */
    private String orderId;
    /**
     * 发起人account
     */
    private String buyer;
    /**
     * 被投诉人account
     */
    private String merchant;
    /**
     * 状态【0:未处理、1:处理中、2:已处理】
     */
    private Integer status;
    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 处理完成时间
     */
    public Date finishTime;
}

package com.lxtx.im.admin.service.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-06-10 20:28
 * @Description
 */
@Data
public class OffsiteArbitrationDetailResp {
    private String id;

    private String orderId;

    private String buyer;

    private String buyerTelephone;

    private String merchant;

    private String merchantTelephone;
    /**
     * 状态【0:未处理、1:处理中、2:已处理】
     */
    private Integer status;
    /**
     * 1 买家胜 2 卖家胜
     */
    private Integer winner;

    public Date createTime;

    public Date updateTime;

    private OffsiteOrderDetailResp orderDetailResp;

    private String complaintId;

    List<OffsiteExchangeComplaintDetailResp> complaintDetailRespList;
    /**
     * 状态【0:未处理、1:处理中、2:已处理】
     */
    private String statusStr;

    /**
     * 订单状态
     */
    private Integer orderStatus;
    private String orderStatusStr;

    /**
     * 处理完成说明
     */
    private String completeContent;
    /**
     * 处理记录
     */
    List<ArbitrationRecordResp> records;

    /**
     * 商家发起
     */
    private Boolean initiativeByMerchant;
}

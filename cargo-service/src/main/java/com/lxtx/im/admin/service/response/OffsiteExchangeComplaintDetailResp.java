package com.lxtx.im.admin.service.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* @description:  线下汇换投诉详情返回
* @author:   CXM
* @create:   2019-04-25 14:45
*/
@Data
public class OffsiteExchangeComplaintDetailResp {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 发起人(钱包用户ID)
     */
    private String userId;
    /**
     * 被投诉人
     */
    private String defendant;
    /**
     * 判断买家是否是投诉人（为了页面判断买卖家）
     */
    private Boolean buyerComplain;
    /**
     * 状态【0:未处理、1:处理中、2:已处理】
     */
    private Integer status;
    private String statusStr;
    /**
     * 图片路径,多个英文逗号分隔
     */
    private List<String> images;
    /**
     * 投诉内容
     */
    private String content;
    /**
     * 联系人手机号
     */
    private String fullTelephone;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    private String orderStatusStr;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 订单详情
     */
    private OffsiteOrderDetailResp order;
    /**
     * 处理完成说明
     */
    private String completeContent;

    /**
     *
     * 处理记录
     */
    List<ArbitrationRecordResp> records;
}



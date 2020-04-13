package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FeignWithdrawApplyFailReq extends BasePageReq {

    private Integer type;

    /**
     * 交易状态
     */
    private Integer status;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 币种ID
     */
    private String coinId;
    /**
     * 交易编号
     */
    private String transferNum;
    /**
     * 申请记录
     */
    private String applyId;

    /**
     * 开始时间-交易时间
     */
    private Date createTime;
    /**
     * 结束时间-交易时间
     */
    private Date updateTime;
}
package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 交易明细参数类
 *
 * @author xufeifei
 * @since 2019-11-23
 */
@Getter
@Setter
public class FeignTradingRecordOtcWithdrawReq extends BasePageReq{

    /**
     * 内部订单号
     */
    private String orderId;

    /**
     *资金托管订单号
     */
    private String orderNum;

    /**
     * 用户钱包ID
     */
    private String userId;

    /**
     * 用户钱包ID集合
     */
    private List<String> userIds;
    /**
     * 到账地址
     */
    private String assignAddr;

    /**
     * 币种
     */
    private String coinId;

    /**
     * 状态
     */
    private Integer state1;

    /**
     *  交易时间
     */
    private Date createTime;
    
    //开始时间-交易时间
    private Date createTimeStart;
    
    //结束时间-交易时间
    private Date createTimeEnd;

    /**
     * 用户钱包ID集合(用户地址查询的)
     */
    private List<String> userIdsByAddr;

    /**
     * 用户钱包ID集合(用户昵称查询的)
     */
    private List<String> userIdsByUserName;

    /**
     * 柬埔寨用户account
     */
    private List<String> khUserAccountList;
}

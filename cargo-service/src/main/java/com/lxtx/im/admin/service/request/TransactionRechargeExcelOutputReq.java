package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 导出资金入账交易记录请求参数类
 *
 * @author CaiRH
 * @since 2019-11-22
 */
@Getter
@Setter
public class TransactionRechargeExcelOutputReq {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 钱包用户ID
     */
    private String walletUserId;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 转出地址
     */
    private String fromAddr;
    /**
     * 到账地址
     */
    private String toAddr;
    /**
     * 币种
     */
    private String coinName;
    /**
     * 外部交易编号
     */
    private String thirdPartyTransferNum;
    /**
     * 外部订单ID
     */
    private String thirdPartyOrderId;
    /**
     * 类型
     */
    private Integer type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date updateTime;

}

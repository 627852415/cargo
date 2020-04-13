package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 资金入账参数类
 *
 * @author CaiRH
 * @since 2019-11-22
 */
@Getter
@Setter
public class FeignTransactionRechargePageReq extends BasePageReq {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 钱包用户ID
     */
    private String walletUserId;
    /**
     * 用户账号集合
     */
    private List<String> accountIds;
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
     * 类型【1：站内提币，2：链上充值，3：OTC充值】
     */
    private Integer type;

    private Date createTime;
    private Date updateTime;

    /**
     * 柬埔寨用户account
     */
    private List<String> khUserAccountList;
    
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 平台用户手机号的国际简码
     */
    private String countryCode;

    /**
     * 电话
     */
    private String telephone;
}

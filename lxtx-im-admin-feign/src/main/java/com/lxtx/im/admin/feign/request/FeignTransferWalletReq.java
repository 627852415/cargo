package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * @author qing
 * @Description 钱包转账
 * @date: 2019年11月22日 上午10:48:14
 */
@Getter
@Setter
public class FeignTransferWalletReq extends BasePageReq {

    // ID
    private String id;

    // 资金托管订单号
    private String transferNum;

    // 转出用户钱包ID(转出用户钱包ID，转出用户昵称)
    private List<String> userIds;

    //转出地址
    private String fromAddr;

    // 接收地址
    private String toAddr;

    // 币种
    private String coinId;

    // 状态
    private Integer status;

    // 开始时间-交易时间
    private Date createTimeStart;

    // 结束时间-交易时间
    private Date createTimeEnd;
    /**
     * 柬埔寨用户account
     */
    private List<String> khUserAccountList;

    private boolean isKHShowAccount;

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
    /**
     * 是否内部账号
     */
    private Boolean isInnerAccount;
}

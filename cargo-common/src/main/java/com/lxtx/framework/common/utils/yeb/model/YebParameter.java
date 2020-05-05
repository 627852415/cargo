package com.lxtx.framework.common.utils.yeb.model;

import lombok.Data;

/**
 * author lin hj on 2019/3/29
 */
@Data
public class YebParameter {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 商户下的唯一用户ID
     */
    private String userId;

    /**
     * 账户类型，principle表示提取本金；interest表示提取收益
     */
    private String  accountType;

    /**
     * 推荐人的BCB钱包地址，如果不填写则默认推荐人都是秘密商户总号的地址
     */
    private String referAddr;

    /**
     * 币种名称，例如DC
     */
    private String coinType;

    /**
     * 存款金额
     */
    private String amount;

    /**
     * 流水号
     */
    private String serial;

    /**
     * 本次存款的业务流水号，由调用一方分配
     */
    private String apikey;

    private String beginTime;

    private String endTime;

    private Integer pageNo;


    private Integer pageSize;


    /**
     * 收款地址
     */
    private String receiver;


    /**
     * 签名值
     */
    private String sign;



}

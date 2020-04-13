package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @description:  商家列表返回
* @author:   CXM
* @create:   2019-03-11 17:06
*/
@Setter
@Getter
public class PayMerchantListResp {

    /**
     * 商家id
     */
    private String id;
    /**
     * im用户名
     */
    private String name;
    /**
     * 地区
     */
    private String countryPhoneCode;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 钱包id
     */
    private String userId;

    /**
     * im用户ID
     */
    private String platformUserId;
    /**
     * 证件号
     */
    private String certificateNo;
    /**
     * 认证状态
     */
    private Integer certificateStatus;
    /**
     * 商家状态【0：正常， 1：冻结】
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

}

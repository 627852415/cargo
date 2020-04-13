package com.lxtx.im.admin.feign.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-12-16 16:54
 * @Description
 */
@Data
public class FeignRechargeTopgateOrderPageReq extends BasePageReq {
    /**
     * ID
     */
    private String id;
    /**
     * 钱包用户ID
     */
    private String userId;

    private String userName;

    /**
     * 第三方生成的订单编号
     */
    private String thirdPartyOrderNo;
    /**
     * 类型
     *
     */
    private Integer type;

    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 支付方式
     */
    private Integer payWay;

    private Date createTime;

    private Date updateTime;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 平台用户手机号的国际简码
     */
    private String countryCode;
    /**
     * 柬埔寨用户account
     */
    private List<String> khUserAccountList;
}

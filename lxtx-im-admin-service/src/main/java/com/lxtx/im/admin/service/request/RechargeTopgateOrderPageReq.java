package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
public class RechargeTopgateOrderPageReq extends BasePageReq {
    /**
     * 钱包用户ID
     */
    private String id;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 平台用户手机号的国际简码
     */
    private String countryCode;
}

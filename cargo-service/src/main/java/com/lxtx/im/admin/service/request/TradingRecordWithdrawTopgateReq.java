package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 交易明细参数类
 *
 * @author hechizhi
 * @since 2020-1-3
 */
@Getter
@Setter
public class TradingRecordWithdrawTopgateReq extends BasePageReq {

    /**
     * 表主键ID
     */
    private String id;

    /**
     * 币支付订单ID
     */
    private String topgateOrderId;

    /**
     * 订单状态【1-处理中 2-成功 3-失败】
     */
    private Integer status;

    /**
     * 钱包用户ID
     */
    private String userId;

    /**
     * 币支付提现方式【1：网银支付,2：微信扫码支付 3、支付宝扫码支付】
     */
    private Integer payWay;

    /**
     * 交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    /**
     * 交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 手机号码
     */
    private String telephone;
    /**
     * 国家简码
     */
    private String countryCode;


}

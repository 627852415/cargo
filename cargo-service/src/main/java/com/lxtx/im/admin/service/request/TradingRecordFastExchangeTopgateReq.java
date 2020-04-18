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
public class TradingRecordFastExchangeTopgateReq extends BasePageReq {

    /**
     * 表主键ID
     */
    private String id;

    /**
     * tgpay的订单编号
     */
    private String thirdPartyOrderNo;

    /**
     * 钱包用户ID
     */
    private String userId;

    /**
     * 支付的币种-币种ID
     */
    private String payCoinId;

    /**
     * 获得的币种-币种ID
     */
    private String gotCoinId;


    /**
     * 订单的状态【1：处理中 2：成功 3：失败】
     */
    private Integer status;

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
     * 平台用户手机号的国际简码
     */
    private String countryCode;

    /**
     * 电话
     */
    private String telephone;

}

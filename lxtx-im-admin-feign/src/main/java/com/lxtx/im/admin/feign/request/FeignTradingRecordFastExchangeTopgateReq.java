package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Topgate闪兑交易明细参数类
 *
 * @author hechizhi
 * @since 2020-1-3
 */
@Getter
@Setter
public class FeignTradingRecordFastExchangeTopgateReq extends BasePageReq{

    private String id;

    /**
     * 资金托管订单编号
     */
    private String thirdPartyOrderNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 钱包用户ID
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 订单的状态
     */
    private Integer status;

    /**
     * 支付的币种-币种ID
     */
    private String payCoinId;

    /**
     * 获得的币种-币种ID
     */
    private String gotCoinId;

    /**
     * 闪兑兑换开始时间
     */
    private Date startTime;
    
    //开始时间-交易时间
    private Date createTimeStart;
    
    //结束时间-交易时间
    private Date createTimeEnd;

    /**
     * 是否柬埔寨演示账号
     */
    private boolean isKHShowAccount;

    /**
     * 平台用户手机号的国际简码
     */
    private String countryCode;

    /**
     * 电话
     */
    private String telephone;

}

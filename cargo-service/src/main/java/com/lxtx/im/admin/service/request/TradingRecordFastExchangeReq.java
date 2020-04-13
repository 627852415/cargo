package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 交易明细参数类
 *
 * @author xufeifei
 * @since 2019-11-23
 */
@Getter
@Setter
public class TradingRecordFastExchangeReq extends BasePageReq {


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
     * 支付的币种-币种名称
     */
    private String payCoin;

    /**
     * 获得的币种-币种ID
     */
    private String gotCoin;

    /**
     * 闪兑兑换开始时间
     */
    private Date startTime;
    
	//交易时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTimeStart;
	
	//交易时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTimeEnd;

    /**
     * 手机号码
     * 2020-01-20
     */
    private String telephone;
    /**
     * 国际简码
     * 2020-01-20
     */
    private String countryCode;

}

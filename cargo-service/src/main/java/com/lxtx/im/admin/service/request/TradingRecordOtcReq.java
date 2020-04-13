package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TradingRecordOtcReq extends BasePageReq {
    /**
     * 内部订单号
     */
    private String orderId;

    /**
     *资金托管订单号
     */
    private String orderNum;

    /**
     * 用户钱包ID
     */
    private String userId;

    /**
     * 用户钱包ID集合
     */
    private List<String> userIds;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 到账地址
     */
    private String assignAddr;

    /**
     * 币种
     */
    private String coinId;

    /**
     * 状态
     */
    private Integer state1;

    /**
     *  交易时间
     */
    private Date createTime;
    
	//交易时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTimeStart;
	
	//交易时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTimeEnd;

    /**
     * 柬埔寨用户account
     */
    private List<String> khUserAccountList;
    /**
     * 国际区号
     */
    private String countryCode;
    /**
     * 手机号
     */
    private String telephone;

}

package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 
 * @Description 钱包转账入参数类
 * @author qing 
 * @date: 2019年11月22日 上午10:48:02
 */
@Getter
@Setter
public class TransferWalletReq extends BasePageReq {
	//ID
	private String id;
	
	// 资金托管订单号
	private String transferNum;
	
	//转出用户钱包ID
	private String userId;
	
	//转出用户名称
	private String userName;
	
	//转出地址
	private String fromAddr;
			
	// 接收地址
	private String toAddr;

	//币种
	private String coinId;
	
	//状态
	private Integer status;
	
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

	private boolean isKHShowAccount;
	
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
    private Integer isInnerAccount;
}

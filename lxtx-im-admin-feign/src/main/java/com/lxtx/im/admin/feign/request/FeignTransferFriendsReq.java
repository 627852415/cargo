package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * 
 * @Description 好友转账
 * @author qing 
 * @date: 2019年11月21日 下午8:48:30
 */
@Getter
@Setter
public class FeignTransferFriendsReq extends BasePageReq{
	
	//ID
	private String id;
	
	//转出用户钱包ID
	private List<String> userIds;
	
	//接收用户钱包ID
	private List<String> receiverIds;

	//币种
	private String coinId;
	
	//状态
	private Integer status;

    //开始时间-交易时间
    private Date createTimeStart;
    
    //结束时间-交易时间
    private Date createTimeEnd;

	/**
	 * 柬埔寨用户account
	 */
	private List<String> khUserAccountList;

	private boolean isKHShowAccount;

}

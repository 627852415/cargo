package com.lxtx.im.admin.service.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import com.lxtx.im.admin.service.utils.ExcelField;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @Description 好友转账
 * @author qing 
 * @date: 2019年11月20日 下午6:15:54
 */
@Getter
@Setter
public class TransferFriendsResp {
	
	//ID
	@ExcelField(name = "ID", orderBy = "1")
	private String id;
	
	//转出用户钱包ID
	@ExcelField(name = "发送用户钱包ID", orderBy = "2")
	private String userId;

	//转出用户国家编码
	@ExcelField(name = "发送用户国际编码", orderBy = "3")
	private String countryCodeOut;
	//转出手机号
	@ExcelField(name = "发送用户手机号", orderBy = "4")
	private String telephoneOut;
	
	//转出用户昵称
	@ExcelField(name = "发送用户昵称", orderBy = "5")
	private String userName;
	
	//接收用户钱包ID
	@ExcelField(name = "接收用户钱包ID", orderBy = "6")
	private String receiverId;
	//转入用户编码
	@ExcelField(name = "接收用户国际编码", orderBy = "7")
	private String countryCodeIn;
	//转入用户电话
	@ExcelField(name = "接收用户手机号", orderBy = "8")
	private String telephoneIn;

	//接收用户昵称
	@ExcelField(name = "接收用户昵称", orderBy = "9")
	private String receiverUserName;
	
	//状态
	private Integer status;
	
	//页面状态显示
	@ExcelField(name = "状态", orderBy = "10")
	private String statusVaule;
	
	//金额
	@ExcelField(name = "金额", orderBy = "11")
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal amount;
	
	//手续费
	@ExcelField(name = "手续费", orderBy = "12")
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal fee;
	
	//币种
	private String coinId;
	
	//币种
	@ExcelField(name = "币种", orderBy = "13")
	private String coinName;
	
	//交易时间
	@ExcelField(name = "交易时间", orderBy = "14")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	
	//类型
	private Integer type;
	
}

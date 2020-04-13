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
 * @Description 钱包转账
 * @author qing 
 * @date: 2019年11月20日 下午6:15:54
 */
@Getter
@Setter
public class TransferWalletResp {
	
	//ID
	@ExcelField(name = "ID", orderBy = "1")
	private String id;
	
	//资金托管订单号
	@ExcelField(name = "资金托管订单号", orderBy = "2")
	private String transferNum;
	
	//转出用户钱包ID
	@ExcelField(name = "转出用户钱包ID", orderBy = "3")
	private String userId;
	
	//转出用户昵称
	@ExcelField(name = "转出用户昵称", orderBy = "4")
	private String userName;
	
	/**
     * 国际简码
     */
    @ExcelField(name = "国家简码", orderBy = "5")
    private String countryCode;

    /**
     * 电话号码
     */
    @ExcelField(name = "电话号码", orderBy = "6")
    private String telephone;
	
	//转出地址
	@ExcelField(name = "转出地址", orderBy = "7")
	private String fromAddr;

	//接收地址
	@ExcelField(name = "接收地址", orderBy = "8")
	private String toAddr;
	
	//类型
	private Integer type;
	
	//是否内部地址
	@ExcelField(name = "是否内部地址", orderBy = "9")
	private String typeVaule;
	
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
	
	//更新时间
	@ExcelField(name = "更新时间", orderBy = "15")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;
	
	//备注
	@ExcelField(name = "备注", orderBy = "16")
	private String remarks;
	
}

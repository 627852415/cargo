package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_account_check_mistake")
public class AccountCheckMistake extends BaseModel implements Serializable {

	private static final long serialVersionUID = -8033770028695808656L;
	/**
	 * 主键ID
	 */
	@TableId
	private String id;
	/**
	 * 对账时间
	 */
	private Date billTime;
	/**
	 * 商家名称
	 */
	private String merchantName;
	/**
	 * 商家编号
	 */
	private String merchantNo;
	/**
	 * 币种ID
	 */
	private String coinType;
	
	/**
	 * 币种名称
	 */
	private String coinName;
	/**
	 * 交易金额
	 */
	private BigDecimal orderAmount;
	/**
	 * 此次交易前可用余额
	 */
	private BigDecimal preAmount;
	/**
	 * 账户应有余额
	 */
	private BigDecimal shouldAmount;
	/**
	 * 账户实际余额
	 */
	private BigDecimal realAmount;
	/**
	 * 可用额差异
	 */
	private BigDecimal diffAmount;
}

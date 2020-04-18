package com.lxtx.im.admin.service.request;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class PatternWhiteListPageReq extends BasePageReq {
	/**
	 * 账号,主键
	 */
	@TableId
	private String account;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 电话
	 */
	private String telephone;
	
	private String countryCode;

	/**
	 * 是否开启白名单 0:否 1:是 2:全部
	 */
	private Integer flag;

	/**
	 * 开始注册时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createBeginTime;

	/**
	 * 结束注册时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createEndTime;
}

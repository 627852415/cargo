package com.lxtx.im.admin.feign.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求某天平台各币种资产
 * @author pengpai
 *
 */
@Data
public class FeignPlatformAllCoinCheckTimeReq extends BasePageReq implements Serializable{

	private static final long serialVersionUID = -8422786310437467231L;
	private String checkTime;
	
	private String coinId;
	
	private String userId;
}

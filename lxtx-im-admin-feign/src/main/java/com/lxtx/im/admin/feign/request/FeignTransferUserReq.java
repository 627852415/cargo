package com.lxtx.im.admin.feign.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @Description 钱包用户ids
 * @author qing 
 * @date: 2019年11月22日 下午4:38:27
 */
@Getter
@Setter
public class FeignTransferUserReq {
	
	private List<String> userIdList;
	
}

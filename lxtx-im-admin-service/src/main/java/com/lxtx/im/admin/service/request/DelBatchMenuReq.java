package com.lxtx.im.admin.service.request;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

/**
 * 
 * @author pengpai
 * @Date: 2018/9/28 16:48
 */
@Data
public class DelBatchMenuReq implements Serializable {

	private static final long serialVersionUID = 1L;
	private Set<String> menuIds;
}

package com.lxtx.im.admin.service.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author pengpai
 * @date 2018/9/28 11:28
 */
@Getter
@Setter
public class MenuIDReq implements Serializable {
	@NotNull(message = "ID不能为空")
	private String menuId;
}

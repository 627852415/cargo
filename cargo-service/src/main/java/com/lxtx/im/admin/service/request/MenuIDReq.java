package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

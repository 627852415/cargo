package com.lxtx.im.admin.service.request;

import lombok.Data;

@Data
public class MenuTreeReq {
	private String name;
	private Integer type;
	private String parentId;
}

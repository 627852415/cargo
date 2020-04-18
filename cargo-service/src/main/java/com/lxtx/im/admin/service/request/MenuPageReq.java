package com.lxtx.im.admin.service.request;

import lombok.Data;

@Data
public class MenuPageReq extends BasePageReq{
	private String name;
	private Integer type;
	private Integer parentId;
}

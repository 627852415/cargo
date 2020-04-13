package com.lxtx.im.admin.service.dto;

import java.util.List;

public interface SysMenuTree {
	void setName(String name);

	void setUrl(String url);

	void addList(List<SysMenuTree> list);
}

package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.dao.model.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author pengpai
 * @date 2018/9/28 11:28
 */
@Data
public class SysMenuReq implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单ID
	 */
	private String menuId;
	/**
	 * 上级菜单ID
	 */
	private String parentId;
	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单URL
	 */
	private String url;

	/**
	 * 国际化key
	 */
	private String textKey;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String perms;

	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	private Integer type;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;

	/**
	 * 菜单Tree
	 */
	private List<SysMenu> children;
}

package com.lxtx.im.admin.dao.model;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Czh Date: 2018/9/27 下午2:28
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu extends BaseModel {
	/**
	 * 菜单ID
	 */
	@TableId
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
	 * 国际化key
	 */
	private String textKey;

	@TableField(exist = false)
	private List<SysMenu> children;
}
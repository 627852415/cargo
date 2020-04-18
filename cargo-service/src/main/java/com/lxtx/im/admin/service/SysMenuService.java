package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.ZreeSelectResp;
import com.lxtx.im.admin.service.response.ZtreeBeanResp;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理业务接口
 * 
 * @author pengpai
 * @Date: 2018/9/27 16:48
 */
public interface SysMenuService {
	/**
	 * 增加菜单
	 *
	 * @param entity
	 * @return
	 */
	BaseResult add(SysMenuReq entity);

	/**
	 * 根据ID批量删除菜单
	 * 
	 * @param ids
	 * @return
	 */
	BaseResult removeBatchByIds(DelBatchMenuReq ids);

	/**
	 * 根据ID删除菜单
	 * 
	 * @param menuId
	 * @return
	 */
	BaseResult delete(String menuId);

	/**
	 * 新增或修改菜单
	 * 
	 * @param entity
	 * @return
	 */
	BaseResult editById(SysMenuReq entity);

	/**
	 * 根据ID查看菜单内容
	 * 
	 * @param id
	 * @return
	 */
	BaseResult findById(MenuIDReq id);

	/**
	 * 根据条件查询菜单列表
	 * 
	 * @param menuPageReq
	 * @return
	 */
	BaseResult findByField(MenuPageReq menuPageReq);

	/**
	 * 查询菜单树
	 * 
	 * @return
	 */
	BaseResult queryMenuTree();

	/**
	 * 根据角色id查询权限
	 * @param roleId
	 * @return
	 */
	Set<String> findAllPerms(String roleId);

    /**
     * 角色授权菜单
     *
     * @param user
     * @param roleId
     * @return
     */
    List<ZtreeBeanResp> treePerms(SysUser user, String roleId);

	/**
	 * 保存或修改
	 * @param req
	 * @return
	 */

	BaseResult saveOrUpdate(SysMenuReq req);

	List<MenuTreeSelectResp> treeSelectList();

	ZreeSelectResp zreeSelect();

	LayuiMenuListResp treegridList(MenuTreeReq req);
}

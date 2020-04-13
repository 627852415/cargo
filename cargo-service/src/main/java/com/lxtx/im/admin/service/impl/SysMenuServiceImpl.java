package com.lxtx.im.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.dao.SysMenuDao;
import com.lxtx.im.admin.dao.SysRoleMenuDao;
import com.lxtx.im.admin.dao.model.SysMenu;
import com.lxtx.im.admin.dao.model.SysRoleMenu;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.Constants.PropertiesContants;
import com.lxtx.im.admin.service.SysMenuService;
import com.lxtx.im.admin.service.SysRoleService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 菜单业务实现
 *
 * @author pengpai
 * @Date: 2018/9/27 16:48
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
	private Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);
	@Autowired
	private SysMenuDao sysMenuDao;

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public BaseResult add(SysMenuReq entity) {
		SysMenu sm = new SysMenu();
		BeanUtils.copyProperties(entity, sm);
		if (sysMenuDao.insert(sm)) {
			return BaseResult.success();
		}
		return BaseResult.error(null, null);
	}

	@Override
	public BaseResult removeBatchByIds(DelBatchMenuReq ids) {
		if (sysMenuDao.deleteBatchIds(ids.getMenuIds())) {
			return BaseResult.success();
		}
		return BaseResult.error(null, null);
	}

	@Override
	public BaseResult delete(String menuId) {
		sysMenuDao.deleteById(menuId);
		//查询下级菜单
		Wrapper<SysMenu> wrapper = new EntityWrapper<>();
		wrapper.eq("parent_id", menuId);
		List<SysMenu> sysMenus = sysMenuDao.selectList(wrapper);
		for(SysMenu menu : sysMenus){
			String childId = menu.getMenuId();
			sysMenuDao.deleteById(childId);
			delete(childId);
		}
		return BaseResult.success();

	}

	@Override
	public BaseResult editById(SysMenuReq entity) {
		SysMenu sm = new SysMenu();
		BeanUtils.copyProperties(entity, sm);
		Wrapper<SysMenu> wrapper = new EntityWrapper<>();
		if (entity.getMenuId() != null) {
			wrapper.eq("menu_id", entity.getMenuId());
			if (sysMenuDao.update(sm, wrapper)) {
				return BaseResult.success();
			}
		}
		return BaseResult.error(null, null);
	}

	@Override
	public BaseResult findById(MenuIDReq menuIDReq) {
		try {
			SysMenu sysMenu = sysMenuDao.selectById(menuIDReq.getMenuId());
			return BaseResult.success(sysMenu);
		} catch (Exception e) {
			log.error("根据菜单ID查询菜单详情=======SysMenuServiceImpl.findById");
			return BaseResult.error(null, null);
		}
	}

	@Override
	public BaseResult findByField(MenuPageReq menuPageReq) {
		try {
			Page<SysMenu> page = Optional.ofNullable(menuPageReq).orElse(new MenuPageReq()).getPage();
			EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
			if (StringUtils.isNotBlank(menuPageReq.getName())) {
				wrapper.like("name", menuPageReq.getName());
			}
			if (menuPageReq.getType() != null) {
				wrapper.eq("type", menuPageReq.getType());
			}
			if (menuPageReq.getParentId() != null) {
				wrapper.eq("parent_id", menuPageReq.getParentId());
			}
			if (menuPageReq.getCurrent() != null) {
				page.setCurrent(menuPageReq.getCurrent());
			}
			if (menuPageReq.getSize() != null) {
				page.setSize(menuPageReq.getSize());
			}
			Page<SysMenu> selectPage = sysMenuDao.selectPage(page, wrapper);
			List<SysMenu> records = selectPage.getRecords();
			for (SysMenu sysMenu : records) {
				if (StringUtils.isBlank(sysMenu.getParentId())) {
					continue;
				}
				SysMenu parentMenu = sysMenuDao.selectById(sysMenu.getParentId());
				if (parentMenu == null) {
					sysMenu.setParentId("");
				} else {
					sysMenu.setParentId(parentMenu.getName());
				}
			}
			selectPage.setRecords(records);
			return BaseResult.success(selectPage);
		} catch (Exception e) {
			log.error("根据字段查询菜单列表异常=======SysMenuServiceImpl.findByField");
			return BaseResult.error(null, null);
		}
	}

	@Override
	public BaseResult queryMenuTree() {
		Wrapper<SysMenu> wrapper = new EntityWrapper<>();
		wrapper.eq("parent_id", 0);
		List<SysMenu> menuList = sysMenuDao.selectList(wrapper);

		for (SysMenu sysMenu : menuList) {
			Wrapper<SysMenu> per = new EntityWrapper<>();
			if (sysMenu.getParentId() != null) {
				per.eq("parent_id", sysMenu.getMenuId());
				List<SysMenu> children = sysMenuDao.selectList(per);
				setChildren(children);
				sysMenu.setChildren(children);
			}
		}
		return BaseResult.success(menuList);
	}

	public void setChildren(List<SysMenu> sysMenuEntities) {
		for (SysMenu sysMenu : sysMenuEntities) {
			Wrapper<SysMenu> per = new EntityWrapper<>();
			if (sysMenu.getParentId() != null) {
				per.eq("parent_id", sysMenu.getMenuId());
				List<SysMenu> children = sysMenuDao.selectList(per);
				setChildren(children);
				sysMenu.setChildren(children);
			}
		}

	}

	@Override
	public Set<String> findAllPerms(String roleId) {
		Wrapper<SysRoleMenu> wrapper = new EntityWrapper<>();
		wrapper.eq("role_id", roleId);
		List<SysRoleMenu> roleMenu = sysRoleMenuDao.selectList(wrapper);
		List<String> menuIds = new ArrayList<>();
		for (SysRoleMenu sysRoleMenu : roleMenu) {
			if (sysRoleMenu != null && StringUtils.isNotBlank(sysRoleMenu.getMenuId())) {
				menuIds.add(sysRoleMenu.getMenuId());
			}
		}
		Wrapper<SysMenu> wrap = new EntityWrapper<>();
		wrap.in("menu_id", menuIds);
		List<SysMenu> selectList = sysMenuDao.selectList(wrap);
		Set<String> perms = new HashSet<>();
		for (SysMenu sysMenu : selectList) {
			if (sysMenu != null && StringUtils.isNotBlank(sysMenu.getPerms())) {
				String perm = sysMenu.getPerms();
				if (perm.contains(",")) {
					String[] split = perm.split(",");
					for (String string : split) {
						perms.add(string);
					}
				} else {
					perms.add(perm);
				}
			}
		}
		return perms;
	}

	@Override
	public List<ZtreeBeanResp> treePerms(SysUser user, String roleId) {
		// 查询列表数据
		List<SysMenu> menuList = null;

		// 只有超级管理员，才能查看所有管理员列表

		List<String> superUsernameList = PropertiesUtil.getList(PropertiesContants.SUPER_USERNAME, ",");
		if (!CollectionUtils.isEmpty(superUsernameList) && superUsernameList.contains(user.getUsername())) {
			menuList = sysMenuDao.selectList(new SysMenu());
		} else {
			menuList = sysRoleService.selectRolePermsByUser(user);
		}

		List<ZtreeBeanResp> ztreeBeans = new ArrayList<>();
		for (SysMenu menu : menuList) {

			ZtreeBeanResp tree = new ZtreeBeanResp();
			tree.setPId(menu.getParentId());
			tree.setName(menu.getName());
			tree.setOpen("true");
			tree.setChkDisabled("false");
			tree.setId(menu.getMenuId());

			SysRoleMenu condition = new SysRoleMenu();
			condition.setRoleId(roleId);
			condition.setMenuId(menu.getMenuId());
			List<SysRoleMenu> sysRoleMenus = sysRoleMenuDao.selectList(condition);
			tree.setChecked(CollectionUtils.isEmpty(sysRoleMenus) ? false : true);

			ztreeBeans.add(tree);
		}
		return ztreeBeans;
	}

	@Override
	public BaseResult saveOrUpdate(SysMenuReq req) {
		SysMenu sysMenu = new SysMenu();
		BeanUtils.copyProperties(req, sysMenu);
		String parentId = sysMenu.getParentId();
		if(StringUtils.isBlank(parentId)){
			sysMenu.setParentId("0");
		}
		boolean b = sysMenuDao.insertOrUpdate(sysMenu);
        if(b){
            return  BaseResult.success("操作成功");
        }else {
            return  BaseResult.error(null,"操作失败");
        }
	}

    @Override
    public List<MenuTreeSelectResp> treeSelectList() {
        Wrapper<SysMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", 0);
        List<SysMenu> menuList = sysMenuDao.selectList(wrapper);
        if(CollectionUtils.isEmpty(menuList)){
            return null;
        }
        List<MenuTreeSelectResp> menuTreeSelectResps = new ArrayList<>();
        for (SysMenu sysMenu : menuList) {
            MenuTreeSelectResp menuTreeSelectResp = new MenuTreeSelectResp();
            menuTreeSelectResp.setId(sysMenu.getMenuId());
            menuTreeSelectResp.setName(sysMenu.getName());
            Wrapper<SysMenu> per = new EntityWrapper<>();
            if (StringUtils.isNotBlank(sysMenu.getParentId())) {
                per.eq("parent_id", sysMenu.getMenuId());
                List<SysMenu> children = sysMenuDao.selectList(per);
                addMenuTreeSelectChild(children, menuTreeSelectResp);
            }
            menuTreeSelectResps.add(menuTreeSelectResp);
        }
        return menuTreeSelectResps;
    }

	private void addMenuTreeSelectChild(List<SysMenu> children, MenuTreeSelectResp menuTreeSelectResp) {
	    if(CollectionUtils.isEmpty(children)){
	        return;
        }
        List<MenuTreeSelectChild> menuTreeSelectChildList = new ArrayList<>();
        for(SysMenu sysMenu : children){
            MenuTreeSelectChild menuTreeSelectChild = new MenuTreeSelectChild();
            menuTreeSelectChild.setId(sysMenu.getMenuId());
            menuTreeSelectChild.setName(sysMenu.getName());
            menuTreeSelectChildList.add(menuTreeSelectChild);
        }
        menuTreeSelectResp.setChildren(menuTreeSelectChildList);
    }

	@Override
	public ZreeSelectResp zreeSelect() {
		Wrapper<SysMenu> wrapper = new EntityWrapper<>();
		wrapper.ne("type", 3);
		List<SysMenu> menuList = sysMenuDao.selectList(wrapper);
		ZreeSelectResp  zreeSelectResp = new ZreeSelectResp();
		if(CollectionUtils.isEmpty(menuList)){
			zreeSelectResp.setCode(1);
			return zreeSelectResp;
		}
		List<Map<String, String>> data = new ArrayList<>();
		for (SysMenu sysMenu : menuList) {
			Map<String, String> map = new HashMap<>();
			map.put("id", sysMenu.getMenuId());
			map.put("pId", sysMenu.getParentId());
			map.put("name", sysMenu.getName());
			map.put("open", "null");
			map.put("chkDisabled", "false");
			data.add(map);
		}
		zreeSelectResp.setData(data);
		return zreeSelectResp;
	}

	@Override
	public LayuiMenuListResp treegridList(MenuTreeReq req) {
		LayuiMenuListResp layuiMenuListResp = new LayuiMenuListResp();
		Wrapper<SysMenu> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(req.getName())) {
			wrapper.like("name", req.getName());
		}
		if (req.getType() != null) {
			wrapper.eq("type", req.getType());
		}
		if (StringUtils.isNotBlank(req.getParentId())) {
			wrapper.eq("parent_id", req.getParentId());
		}
		layuiMenuListResp.setData(sysMenuDao.selectList(wrapper));
		return layuiMenuListResp;
	}

}

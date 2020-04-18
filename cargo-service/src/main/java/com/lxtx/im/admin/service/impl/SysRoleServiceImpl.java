package com.lxtx.im.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.SysMenuDao;
import com.lxtx.im.admin.dao.SysRoleDao;
import com.lxtx.im.admin.dao.SysRoleMenuDao;
import com.lxtx.im.admin.dao.SysUserRoleDao;
import com.lxtx.im.admin.dao.model.*;
import com.lxtx.im.admin.service.SysRoleService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.SysRoleInfoResp;
import com.lxtx.im.admin.service.response.SysRolePageResp;
import com.lxtx.im.admin.service.response.SysRoleResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public SysRolePageResp listPage(SysRoleReq req) {
        //查询所有角色
        EntityWrapper<SysRole> ew = new EntityWrapper<>();
        if (StringUtils.isNotBlank(req.getRoleName())) {
            ew.like("role_name", req.getRoleName());
        }
        ew.orderBy("create_time", false);
        Page page = sysRoleDao.selectPage(req.getPage(), ew);

        List<SysRole> records = page.getRecords();
        SysRolePageResp resp = new SysRolePageResp();
        if (CollectionUtils.isEmpty(records)) {
            return resp;
        }
        BeanUtils.copyProperties(page, resp);

        List<SysRoleResp> roleRespList = new ArrayList<>();
        for (SysRole role : records) {
            if (role == null) {
                continue;
            }
            SysRoleResp roleResp = new SysRoleResp();
            BeanUtils.copyProperties(role, roleResp);
            roleRespList.add(roleResp);
        }
        resp.setRecords(roleRespList);
        return resp;
    }

    @Override
    public void delete(SysRoleDeleteReq req) {
        sysRoleDao.deleteById(req.getRoleId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult save(SysRoleSaveReq req) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(req.getRoleName());
        SysRole roleSelect = sysRoleDao.selectOne(sysRole);
        if (roleSelect != null) {
            throw LxtxBizException.newException("该角色已经存在！");
        }
        //插入角色表
        SysRole saveRole = new SysRole();
        BeanUtils.copyProperties(req, saveRole);
        saveRole.setCreateBy(ShiroUtils.getUserId());
        saveRole.setUpdateBy(ShiroUtils.getUserId());
        sysRoleDao.insert(saveRole);

        if (req.getMenuIdList().length > 0) {
            List<SysRoleMenu> roleMenuList = new ArrayList<>();
            for (String menuId : req.getMenuIdList()) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(saveRole.getRoleId());
                sysRoleMenu.setCreateBy(ShiroUtils.getUserId());
                sysRoleMenu.setUpdateBy(ShiroUtils.getUserId());
                roleMenuList.add(sysRoleMenu);
            }
            //插入角色菜单中间表
            sysRoleMenuDao.insertBatch(roleMenuList);
        }
        return BaseResult.success();
    }

    @Override
    public SysRoleInfoResp info(SysRoleInfoReq req) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(req.getRoleId());
        SysRole roleSelect = sysRoleDao.selectOne(sysRole);
        SysRoleInfoResp roleInfoResp = new SysRoleInfoResp();
        if (roleSelect == null) {
            throw LxtxBizException.newException("该角色不存在！");
        }
        BeanUtils.copyProperties(roleSelect, roleInfoResp);

        //查询角色菜单中间表

        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId(roleSelect.getRoleId());
        List<SysRoleMenu> roleMenuList = sysRoleMenuDao.selectList(sysRoleMenu);
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return roleInfoResp;
        }
        List<String> idList = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenuList) {
            idList.add(roleMenu.getMenuId());
        }
        roleInfoResp.setMenuIdList(idList);
        return roleInfoResp;
    }

    @Override
    public List<SysMenu> selectRolePermsByUser(SysUser user) {

        SysUserRole condition = new SysUserRole();
        condition.setUserId(user.getUserId());

        //查询角色
        List<SysUserRole> sysUserRoles = sysUserRoleDao.selectList(condition);
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            return null;
        }
        List<String> menuIdList = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoles) {
            SysRoleMenu query = new SysRoleMenu();
            query.setRoleId(sysUserRole.getRoleId());
            List<SysRoleMenu> sysRoleMenus = sysRoleMenuDao.selectList(query);
            for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
                menuIdList.add(sysRoleMenu.getMenuId());
            }
        }
        if (CollectionUtils.isEmpty(menuIdList)) {
            return null;
        }
        List<SysMenu> permsList =  sysMenuDao.selectBatchIds(menuIdList);
        if (CollectionUtils.isEmpty(permsList)) {
            return permsList;
        }
        for (SysMenu sysMenu : permsList){
            if (sysMenu == null || StringUtils.isBlank(sysMenu.getPerms())) {
                permsList.remove(sysMenu);
            }
        }
        return permsList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateSysRole req) {

        SysRole role = new SysRole();
        BeanUtils.copyProperties(req, role);
        role.setUpdateBy(ShiroUtils.getUserId());
        sysRoleDao.updateById(role);


        //更新角色与菜单关系
        saveOrUpdate(role.getRoleId(), req.getMenuIdList());

    }

    private void saveOrUpdate(String roleId, List<String> menuIdList) {
        if (CollectionUtils.isEmpty(menuIdList)) {
            return;
        }
        SysRoleMenu condition = new SysRoleMenu();
        condition.setRoleId(roleId);
        //先删除角色与菜单关系
        sysRoleMenuDao.delete(condition);

        List<SysRoleMenu> roleMenuList = new ArrayList<>(menuIdList.size());
        //保存角色与菜单关系
        for (String menuId : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            roleMenuList.add(sysRoleMenu);
        }

        //插入角色菜单中间表
        sysRoleMenuDao.insertBatch(roleMenuList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult findAll() {
        SysRole sysRole = new SysRole();
        sysRole.setDelFlag(false);
        List<SysRole> sysRoles = sysRoleDao.selectList(sysRole);
        return BaseResult.success(sysRoles);
    }

}

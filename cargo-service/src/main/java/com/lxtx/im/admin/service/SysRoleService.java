package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.SysMenu;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.SysRoleInfoResp;
import com.lxtx.im.admin.service.response.SysRolePageResp;

import java.util.List;

/**
 * @description: 角色管理
 * @author: CXM
 * @create: 2018-09-27 15:38
 */
public interface SysRoleService {
    /**
     * 获取角色列表数据
     *
     * @param sysRoleReq
     * @return
     */
    SysRolePageResp listPage(SysRoleReq sysRoleReq);

    /**
     * 删除角色
     *
     * @param sysRoleDeleteReq
     * @return
     */
    void delete(SysRoleDeleteReq sysRoleDeleteReq);

    /**
     * 保存角色
     *
     * @param sysRoleSaveReq
     */
    BaseResult save(SysRoleSaveReq sysRoleSaveReq);

    /**
     * 角色信息返回
     *
     * @param sysRoleInfoReq
     * @return
     */
    SysRoleInfoResp info(SysRoleInfoReq sysRoleInfoReq);

    /**
     * 根据用户获取对应的角色权限
     *
     * @param user
     * @return
     */
    List<SysMenu> selectRolePermsByUser(SysUser user);

    /**
     * @param req
     */
    void update(UpdateSysRole req);

    /**
     * 查找用户角色
     *
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-10-12 0012
     */
    BaseResult findAll();

}

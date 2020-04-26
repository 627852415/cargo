package com.lxtx.im.admin.web.admin;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.SysRoleService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 角色管理
 * @author: CXM
 * @create: 2018-09-27 14:41
 **/
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色列表主页跳转
     *
     * @return
     */
    @SysLogAop("角色列表主页跳转")
    @GetMapping(value = "/index")
    @RequiresPermissions("sys:role:list")
    public String toIndex() {
        return "role/role-index";
    }

    /**
     * 角色列表
     *
     * @return
     */
    @SysLogAop("删除角色")
    @PostMapping(value = "/list")
    @RequiresPermissions("sys:role:list")
    @ResponseBody
    public BaseResult listPage(@Valid @RequestBody SysRoleReq req) {
        return BaseResult.success(sysRoleService.listPage(req));
    }

    /**
     * 删除角色
     *
     * @param sysRoleDeleteReq
     * @return
     */
    @PostMapping(value = "/delete")
    @RequiresPermissions("sys:role:list")
    @ResponseBody
    @SysLogAop(value = "删除角色", monitor = true)
    public BaseResult delete(@Valid @RequestBody SysRoleDeleteReq sysRoleDeleteReq) {
        sysRoleService.delete(sysRoleDeleteReq);
        return BaseResult.success();
    }

    /**
     * 角色新增页
     *
     * @return
     */
    @SysLogAop("角色新增页")
    @GetMapping(value = "/add")
    @RequiresPermissions("sys:role:list")
    public String add() {
        return "role/role-save";
    }

    /**
     * 保存角色
     *
     * @param sysRoleSaveReq
     * @return
     */
    @SysLogAop(value = "保存角色", monitor = true)
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:role:list")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody SysRoleSaveReq sysRoleSaveReq) {
        return sysRoleService.save(sysRoleSaveReq);
    }

    /**
     * 根据id获取角色信息
     *
     * @param sysRoleInfoReq
     * @return
     */
    @SysLogAop("获取角色信息")
    @GetMapping(value = "/info")
    @RequiresPermissions("sys:role:list")
    public String info(SysRoleInfoReq sysRoleInfoReq, Model model) {
        model.addAttribute("role", sysRoleService.info(sysRoleInfoReq));
        return "role/role-edit";
    }

    /**
     * 修改角色
     */
    @SysLogAop(value = "修改角色", monitor = true)
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:list")
//    @RequiresPermissions("sys:role:update")
    public BaseResult update(@RequestBody UpdateSysRole req) {
        sysRoleService.update(req);
        return BaseResult.success();
    }

    /**
     * 查找用户角色
     *
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-10-12 0012
     */
    @SysLogAop("查找用户角色")
    @ResponseBody
    @PostMapping("/findAll")
    @RequiresPermissions("sys:role:list")
    public BaseResult findAll() {
        return sysRoleService.findAll();
    }

}

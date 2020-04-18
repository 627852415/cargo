package com.lxtx.im.admin.web.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.SysMenu;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.SysMenuService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.ZreeSelectResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单管理控制
 *
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public String list() {
        return "menu/list";
    }

    public SysUser getUser() {
        return ShiroUtils.getUserEntity();
    }

    public String getUserId() {
        return getUser().getUserId();
    }

    @RequestMapping("/index")
    @RequiresPermissions("sys:menu:list")
    public String index() {
        return "sysmenu/sysMenu-index";
    }

    /**
     * 获取菜单列表
     */
    @RequestMapping("/show/list")
    @RequiresPermissions("sys:menu:list")
    @ResponseBody
    public BaseResult menuList(MenuPageReq mpr) {
        return sysMenuService.findByField(mpr);
    }

    /**
     * 获取菜单树
     */
    @RequestMapping("/show/tree")
    @RequiresPermissions("sys:menu:list")
    @ResponseBody
    public  BaseResult menuTree() {
        return sysMenuService.queryMenuTree();
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:menu:list")
    public String  info(MenuIDReq mir, Model model) {
        BaseResult result = sysMenuService.findById(mir);
        model.addAttribute("sysMenu", JSONObject.parseObject(JSON.toJSONString(result.getData()), SysMenu.class));
        return "sysmenu/sysMenu-save";
    }

    /**
     * @author pengpai
     * @Description 跳转到新增页面
     * @date 2018/9/28 11:10
     **/
    @RequestMapping("/add")
    @RequiresPermissions("sys:menu:list")
    public String add() {
        return "sysmenu/sysMenu-save";
    }

    /**
     * 新增
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:list")
    @ResponseBody
    @SysLogAop(value = "新增菜单", monitor = true)
    public  BaseResult save(@Valid @RequestBody SysMenuReq menu) {
        return sysMenuService.saveOrUpdate(menu);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:list")
    @ResponseBody
    @SysLogAop(value = "修改菜单", monitor = true)
    public  BaseResult update(@Valid @RequestBody SysMenuReq menu) {
        return sysMenuService.saveOrUpdate(menu);
    }


    /**
     * 批量删除
     */
    @RequestMapping("/delete/batch")
    @RequiresPermissions("sys:menu:list")
//	@RequiresPermissions("sys:menu:delete")
    @ResponseBody
    @SysLogAop(value = "批量删除菜单", monitor = true)
    public BaseResult deleteBatch(@RequestBody DelBatchMenuReq menuIds) {
        return sysMenuService.removeBatchByIds(menuIds);
    }

    /**
     * 删除单条
     */
    @RequestMapping("/delete/one")
    @RequiresPermissions("sys:menu:list")
//	@RequiresPermissions("sys:menu:delete")
    @ResponseBody
    @SysLogAop(value = "删除菜单", monitor = true)
    public BaseResult deleteOne(@RequestBody MenuIDReq req) {
        return sysMenuService.delete(req.getMenuId());
    }

    /**
     * 角色授权菜单
     */
    @ResponseBody
    @RequestMapping("/treePerms")
    @RequiresPermissions("sys:menu:list")
    @SysLogAop(value = "角色授权")
    public BaseResult treePerms(String roleId) {
        return BaseResult.success(sysMenuService.treePerms(getUser(),roleId));
    }

    /**
     * 获取上级菜单
     */
    @RequestMapping("/treeSelectList")
    @RequiresPermissions("sys:menu:list")
    @ResponseBody
    public  List<MenuTreeSelectResp> treeSelectList() {
        return sysMenuService.treeSelectList();
    }

    /**
     * zree下拉树
     */
    @RequestMapping("/zreeSelect")
    @RequiresPermissions("sys:menu:list")
    @ResponseBody
    public ZreeSelectResp zreeSelect() {
        return sysMenuService.zreeSelect();
    }

    /**
     * 获取菜单列表
     */
    @RequestMapping("/treegridList")
    @RequiresPermissions("sys:menu:list")
    @ResponseBody
    public LayuiMenuListResp treegridList(MenuTreeReq req) {
        return sysMenuService.treegridList(req);
    }
}

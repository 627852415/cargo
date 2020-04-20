package com.lxtx.im.admin.web.admin;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.SysUserService;
import com.lxtx.im.admin.service.request.SysUserListPageReq;
import com.lxtx.im.admin.service.request.SysUserModifyReq;
import com.lxtx.im.admin.service.request.SysUserPassModifyReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author liyunhua
 * @Date 2018-09-28 0028
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @SysLogAop("系统用户管理首页")
    @GetMapping("/index")
    @RequiresPermissions("sys:user:list")
    public String index() {
        return "sysUser/sysUser-index";
    }

    @SysLogAop("系统用户管理密码修改跳转")
    @GetMapping("/toUpdatePass")
    @RequiresPermissions("sys:user:list")
    public String toUpdatePass(String userId, HttpServletRequest request) {
        request.setAttribute("userId",userId);
        return "sysUser/sysUser-updatePass";
    }

    @SysLogAop(value = "系统用户管理密码修改", monitor = true, param = false)
    @PostMapping("/updatePasswords")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public BaseResult updatePass2(@Valid @RequestBody  SysUserPassModifyReq req) {
        return sysUserService.modify2(req);
    }


    /**
     * 获取系统用户列表
     *
     * @param req
     * @return
     */
    @SysLogAop("获取系统用户列表")
    @PostMapping("/listPage")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public BaseResult listPage(SysUserListPageReq req) {
        return sysUserService.listPage(req);
    }

    /**
     * 新增用户
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "新增用户", monitor = true, param = false)
    @PostMapping("/add")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public BaseResult add(@RequestBody SysUserModifyReq req) {
        return sysUserService.modify(req);
    }

    /**
     * 修改用户
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "修改用户", monitor = true)
    @PostMapping("/modify")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public BaseResult modify(@RequestBody SysUserModifyReq req) {
        return sysUserService.modify(req);
    }

    /**
     * 删除用户
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "删除用户", monitor = true)
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public BaseResult delete(SysUserModifyReq req) {
        return sysUserService.delete(req);
    }

    /**
     * 重置用户密码为123456
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "重置密码", monitor = true)
    @PostMapping("/resetPwd")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public BaseResult resetPwd(SysUserModifyReq req) {
        return sysUserService.resetPwd(req);
    }

    /**
     * 启用用户
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "启用用户", monitor = true)
    @PostMapping("/enable")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public BaseResult enable(SysUserModifyReq req) {
        return sysUserService.statusChange(req);
    }

    /**
     * 禁用用户
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "禁用用户", monitor = true)
    @PostMapping("/disable")
    @RequiresPermissions("sys:user:list")
    @ResponseBody
    public BaseResult disable(SysUserModifyReq req) {
        return sysUserService.statusChange(req);
    }


}

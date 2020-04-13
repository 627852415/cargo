package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.UserAuthenticationService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.UserAuthenticationAuditReq;
import com.lxtx.im.admin.service.request.UserAuthenticationDelReq;
import com.lxtx.im.admin.service.request.UserAuthenticationDetailReq;
import com.lxtx.im.admin.service.request.UserAuthenticationEditReq;
import com.lxtx.im.admin.service.request.UserAuthenticationPageReq;
import com.lxtx.im.admin.service.request.UserAuthenticationUpdateStatusReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户身份认证
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Controller
@RequestMapping("/user/authentication")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @SysLogAop("用户身份认证首页")
    @GetMapping("/index")
    @RequiresPermissions("user:authentication:index")
    public String index() {
        return "user/user-authentication-index";
    }

    @SysLogAop("用户身份认证列表")
    @PostMapping(value = "/page")
    @RequiresPermissions("user:authentication:index")
    @ResponseBody
    public BaseResult page(UserAuthenticationPageReq req){
        return userAuthenticationService.page(req);
    }

    @SysLogAop(value = "更新用户身份认证状态",monitor = true)
    @PostMapping(value = "/update/status")
    @RequiresPermissions("user:authentication:index")
    @ResponseBody
    public BaseResult updateStatus(@RequestBody UserAuthenticationUpdateStatusReq req){
        return userAuthenticationService.updateStatus(req);
    }

    @SysLogAop(value = "更新用户身份认证状态",monitor = true)
    @PostMapping(value = "/audit")
    @RequiresPermissions("user:authentication:index")
    @ResponseBody
    public BaseResult audit(UserAuthenticationAuditReq req){
        return userAuthenticationService.audit(req);
    }

    @SysLogAop(value = "更新用户身份认证信息",monitor = true)
    @PostMapping(value = "/edit")
    @RequiresPermissions("user:authentication:index")
    @ResponseBody
    public BaseResult edit(UserAuthenticationEditReq req){
        return userAuthenticationService.edit(req);
    }

    @SysLogAop("用户身份认证详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("user:authentication:index")
    public String detail(UserAuthenticationDetailReq req, Model model){
        return userAuthenticationService.detail(req, model);
    }

    @SysLogAop(value = "删除用户身份认证信息",monitor = true)
    @PostMapping(value = "/del")
    @RequiresPermissions("user:authentication:index")
    @ResponseBody
    public BaseResult del(@RequestBody UserAuthenticationDelReq req){
        return userAuthenticationService.del(req);
    }

}

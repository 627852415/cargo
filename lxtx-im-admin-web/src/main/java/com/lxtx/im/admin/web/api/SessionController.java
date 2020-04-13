package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.SessionService;
import com.lxtx.im.admin.service.request.UserDevicePageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 在线用户
 * @author: CXM
 * @create: 2018-09-05 16:25
 **/
@Controller
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

    /**
     * 跳转在线用户列表
     *
     * @return
     */
    @SysLogAop("跳转在线用户列表")
    @GetMapping("/index")
    @RequiresPermissions("session:index")
    public String onlineIndex() {
        return "session/session-index";
    }

    /**
     * 在线用户列表
     *
     * @param req
     * @return
     */
    @SysLogAop("在线用户列表")
    @PostMapping("/list")
    @RequiresPermissions("session:index")
    @ResponseBody
    public BaseResult listPage(UserDevicePageReq req) throws Exception {
        return sessionService.listPage(req);
    }



}

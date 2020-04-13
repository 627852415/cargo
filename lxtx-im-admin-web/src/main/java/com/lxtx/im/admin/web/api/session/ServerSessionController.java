package com.lxtx.im.admin.web.api.session;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.ServerSessionService;
import com.lxtx.im.admin.service.request.UserDevicePageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2020/1/13 15:13
 */
@RestController
@RequestMapping("/server/session")
public class ServerSessionController {
    @Autowired
    private ServerSessionService serverSessionService;

    @SysLogAop("获取服务器session")
    @PostMapping("/get")
    @ResponseBody
    public BaseResult getAttribute(HttpServletRequest request) {
        return serverSessionService.getSession(request);
    }
}

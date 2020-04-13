package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.ExceptionRecordService;
import com.lxtx.im.admin.service.request.ExceptionHandleReq;
import com.lxtx.im.admin.service.request.ExceptionRecordReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/exception")
public class ExceptionRecordController {

    @Autowired
    private ExceptionRecordService exceptionRecordService;

    @SysLogAop("异常处理首页")
    @GetMapping(value = "/toPage")
    public String toPage() {
        return "admin/exception-handle";
    }

    @SysLogAop("异常处理处理")
    @PostMapping(value = "/record")
    @ResponseBody
    public BaseResult record(ExceptionRecordReq req, HttpSession session) {
        return exceptionRecordService.record(req, session);
    }

    @SysLogAop(value = "异常处理", monitor = true)
    @PostMapping(value = "/handle")
    @ResponseBody
    @RequiresPermissions("exception:handle")
    public BaseResult handle(ExceptionHandleReq req, HttpSession session) {
        return exceptionRecordService.handle(req, session);
    }
}



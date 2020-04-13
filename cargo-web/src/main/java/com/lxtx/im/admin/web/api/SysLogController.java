package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.SysLogService;
import com.lxtx.im.admin.service.request.SysLogListPageReq;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 操作日志控制器
 *
 * @Author liyunhua
 * @Date 2018-09-28 0028
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @GetMapping("/index")
    @RequiresPermissions("sys:log:index")
    public String index() {
        return "sysLog/sysLog-index";
    }

    /**
     * 获取操作日志列表
     *
     * @param req
     * @return
     */
    @PostMapping("/listPage")
    @RequiresPermissions("sys:log:index")
    @ResponseBody
    public BaseResult listPage(SysLogListPageReq req) {
        return sysLogService.listPage(req);
    }


}

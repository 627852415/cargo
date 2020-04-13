package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.SmsService;
import com.lxtx.im.admin.service.request.SmsListPageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 短信管理控制器
 *
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/index")
    @SysLogAop("搜索短信列表首页")
    @RequiresPermissions("sms:index")
    public String toIndex(){
        return "sms/sms-index";
    }

    /**
     * 搜索短信列表
     * @param req
     * @return
     */
    @SysLogAop("搜索短信列表")
    @PostMapping(value = "/list")
    @RequiresPermissions("sms:index")
    @ResponseBody
    public BaseResult list(SmsListPageReq req) {
        return smsService.list(req);
    }

}

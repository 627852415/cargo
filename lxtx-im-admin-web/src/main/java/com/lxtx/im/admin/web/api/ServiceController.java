package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.RefreshScopeService;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Czh
 * Date: 2018/10/19 下午1:38
 */
@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private RefreshScopeService refreshScopeService;

    @SysLogAop(value = "刷新对应应用配置", monitor = true)
    @PostMapping("/refresh/scope")
    @RequiresPermissions("service:index")
    @ResponseBody
    public BaseResult refreshScope(String serviceName) {
        return refreshScopeService.refreshScope(serviceName);
    }

    @SysLogAop("获取应用服务名称列表")
    @PostMapping("/get/service/list")
    @RequiresPermissions("service:index")
    @ResponseBody
    public BaseResult getServiceList(){
        return refreshScopeService.getServiceList();
    }

    /**
     * 首页
     * @return
     */
    @SysLogAop("应用配置首页")
    @GetMapping("/index")
    @RequiresPermissions("service:index")
    public String index(){
        return "service/service-index";
    }

}
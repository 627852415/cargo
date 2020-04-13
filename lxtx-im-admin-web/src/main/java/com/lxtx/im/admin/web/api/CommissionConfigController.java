package com.lxtx.im.admin.web.api;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CommissionConfigService;
import com.lxtx.im.admin.service.request.CommissionConfigAddReq;
import com.lxtx.im.admin.service.request.CommissionConfigListReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 分佣设置表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-03-09
 */
@Controller
@RequestMapping("/commission/config")
public class CommissionConfigController {

    /**
     * 返佣设置列表
     *
     * @return
     */
    @SysLogAop("跳转返佣设置列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("commission:config:index")
    public String toIndex() {
        return "commissionConfig/commission-config-index";
    }

    @Autowired
    private CommissionConfigService commissionConfigService;

    @PostMapping("/add")
    @ResponseBody
    public BaseResult configAdd(@Valid @RequestBody List<CommissionConfigAddReq> req) {
        return commissionConfigService.addConfig(req);
    }

    @PostMapping("/list")
    @ResponseBody
    public BaseResult configList(CommissionConfigListReq req) {
        return commissionConfigService.configList(req);
    }
}


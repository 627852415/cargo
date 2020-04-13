package com.lxtx.im.admin.web.api;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CommissionExtraConfigService;
import com.lxtx.im.admin.service.request.CommissionExtraConfigAddReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 额外分佣配置表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
@Controller
@RequestMapping("/commission/extra/config")
public class CommissionExtraConfigController {

    @Autowired
    private CommissionExtraConfigService commissionExtraConfigService;

    /**
     * 额外分佣通用配置列表
     *
     * @return
     */
    @SysLogAop("跳转额外分佣通用配置列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("commission:extra:config:index")
    public String toIndex() {
        return "commissionWhitelist/commission-extra-config-index";
    }


    /**
     * 额外分佣配置列表
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public BaseResult selectExtraConfigList(){
        return commissionExtraConfigService.selectExtraConfigList();
    }

    /**
     * 新增、更新额外分佣配置
     * @param req
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public BaseResult addExtraConfig(@RequestBody List<CommissionExtraConfigAddReq> req){
        return commissionExtraConfigService.addExtraConfig(req);
    }
}


package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AppVersionService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  APP版本
 * @author tangdy
 */
@Controller
@RequestMapping("/appVersion")
public class AppVersionController {

    @Autowired
    private AppVersionService appVersionService;

    /**
     * 版本首页
     * @return
     */
    @SysLogAop("跳转APP版本首页")
    @GetMapping("/index")
    @RequiresPermissions("appVersion:index")
    public String index(){
        return "appversion/app-version-index";
    }

    /**
     * 列表
     * @param req
     * @return
     */
    @SysLogAop("查询APP版本列表数据")
    @PostMapping("/listPage")
    @ResponseBody
    @RequiresPermissions("alertAssets:index")
    public BaseResult listPage(AppVersionListPageReq req){
        return appVersionService.listPage(req);
    }

    /**
     * 跳转到新增或编辑页面
     * @return
     */
    @SysLogAop("跳转APP版本新增/编辑页")
    @GetMapping("/modifyPage")
    @RequiresPermissions("appVersion:index")
    public String modifyPage(AppVersionSelectReq req, Model model){
        if(StringUtils.isNotBlank(req.getId())){
            appVersionService.info(req, model);
        }
        return "appversion/app-version-save";
    }

    /**
     * 新增或编辑
     * @return
     */
    @SysLogAop(value = "APP版本新增或编辑", monitor = true)
    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions("alertAssets:index")
    public BaseResult save(@Valid @RequestBody AppVersionSaveReq req){
        return appVersionService.save(req);
    }

    /**
     * 版本删除
     * @param req
     * @return
     */
    @SysLogAop(value = "删除APP版本", monitor = true)
    @PostMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("alertAssets:index")
    public BaseResult delete(@Valid @RequestBody AppVersionDeleteReq req) {
        return appVersionService.delete(req);
    }
}

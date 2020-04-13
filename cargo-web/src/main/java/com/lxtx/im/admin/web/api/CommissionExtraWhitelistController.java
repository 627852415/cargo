package com.lxtx.im.admin.web.api;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CommissionExtraWhitelistService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 额外分佣白名单表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
@Controller
@RequestMapping("/commission/extra/whitelist")
public class CommissionExtraWhitelistController {

    @Autowired
    private CommissionExtraWhitelistService commissionExtraWhitelistService;

    /**
     * 白名单管理列表
     *
     * @return
     */
    @SysLogAop("跳转白名单管理列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("commission:whitelist:index")
    public String toIndex() {
        return "commissionWhitelist/commission-whitelist-index";
    }

    /**
     * 额外分佣白名单列表
     * @param req
     * @return
     */
    @PostMapping("/page/list")
    @ResponseBody
    public BaseResult selectExtraWhitelistPage(CommissionExtraWhitelistListReq req){
        return commissionExtraWhitelistService.selectExtraWhitelistPage(req);
    }

    /**
     * 新增页面
     */
    @SysLogAop(value = "级别新增页面",monitor = false)
    @GetMapping(value = "/toAdd")
    @RequiresPermissions("whitelist:index")
    public String toAdd(Model model) {
        return "commissionWhitelist/whitelist-add";
    }

    /**
     * 获取白名单国家列表
     * @param req
     * @return
     */
    @PostMapping("/global/list")
    @ResponseBody
    public BaseResult selectWhitelist(CommissionWhitelistReq req){
        return commissionExtraWhitelistService.selectWhitelist(req);
    }

    /**
     * 新增、更新额外分佣白名单
     * @param req
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public BaseResult addExtraWhitelist(@RequestBody  CommissionExtraWhitelistAddReq req){
        return commissionExtraWhitelistService.addExtraWhitelist(req);
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop("级别编辑")
    @GetMapping(value = "/toEdit")
    @RequiresPermissions("level:index")
    public String edit(CommissionExtraWhitelistReq req, Model model) {
        model.addAttribute("countryName",req.getCountryName());
        model.addAttribute("id",req.getId());
        return "commissionWhitelist/whitelist-edit";
    }

    /**
     * 移除额外分佣白名单
     * @param req
     * @return
     */
    @PostMapping("/del")
    @ResponseBody
    public BaseResult delExtraWhitelist(@RequestBody CommissionExtraWhitelistReq req){
        return commissionExtraWhitelistService.delExtraWhitelist(req);
    }

    /**
     * 地区分佣比例编辑列表
     * @param req
     * @return
     */
    @PostMapping("/config/list")
    @ResponseBody
    public BaseResult selectExtraConfigList(CommissionExtraWhitelistReq req){
        return commissionExtraWhitelistService.selectExtraConfigList(req);
    }
}


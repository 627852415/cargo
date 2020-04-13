package com.lxtx.im.admin.web.api;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CommissionLevelService;
import com.lxtx.im.admin.service.request.AdDetailByIdReq;
import com.lxtx.im.admin.service.request.CommissionLevelAddReq;
import com.lxtx.im.admin.service.request.CommissionLevelByIdReq;
import com.lxtx.im.admin.service.request.CommissionLevelListReq;
import com.lxtx.im.admin.service.response.AdDetailResp;
import com.lxtx.im.admin.service.response.CommissionLevelResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 分佣级别管理表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-03-09
 */
@Controller
@RequestMapping("/commission/level")
public class CommissionLevelController {

    @Autowired
    private CommissionLevelService commissionLevelService;

    /**
     * 返佣级别列表
     *
     * @return
     */
    @SysLogAop("跳转返佣级别列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("commission:level:index")
    public String toIndex() {
        return "commissionLevel/commission-level-index";
    }

    @PostMapping("/list")
    @ResponseBody
    public BaseResult levelList(CommissionLevelListReq req) {
        return commissionLevelService.levelList(req);
    }

    /**
     * 新增页面
     */
    @SysLogAop(value = "级别新增页面",monitor = false)
    @GetMapping(value = "/toAdd")
    @RequiresPermissions("level:index")
    public String toAdd(Model model) {
        return "commissionLevel/level-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public BaseResult levelAdd(@Valid @RequestBody CommissionLevelAddReq req) {
        return commissionLevelService.addLevel(req);
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop("级别编辑")
    @GetMapping(value = "/toEdit")
    @RequiresPermissions("level:index")
    public String edit(CommissionLevelByIdReq req, Model model) {
        BaseResult result = commissionLevelService.getLevel(req);
        String resultJsona = JSONArray.toJSONString(result.getData());
        CommissionLevelResp resp = JSONObject.parseObject(resultJsona, CommissionLevelResp.class);
        model.addAttribute("detail",resp);
        return "commissionLevel/level-add";
    }

    @PostMapping("/getLevel")
    @ResponseBody
    public BaseResult getLevel(CommissionLevelByIdReq req) {
        return BaseResult.success(commissionLevelService.getLevel(req));
    }

    @PostMapping("/all")
    @ResponseBody
    public BaseResult levelAll() {
        return commissionLevelService.levelAll();
    }

}


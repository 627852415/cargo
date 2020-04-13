package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.TopgateRechargePayWayService;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

@Controller
@RequestMapping("/topgate/recharge/payway")
public class TopgateRechargePayWayController {

    @Autowired
    private TopgateRechargePayWayService payWayService;

    /**
     * topgate 充值支付方式配置首页
     */
    @SysLogAop("充值支付方式配置首页")
    @GetMapping("/index")
    @RequiresPermissions("topgate:recharge:payway:index")
    public String index() {
        return "wallet/topgate-recharge-index";
    }

    /**
     * 跳转到新增页面
     */
    @SysLogAop(value = "充值支付方式配置新增页",monitor = false)
    @GetMapping(value = "/add")
    @RequiresPermissions("add:topgate:recharge:payway")
    public String add(Model model, Locale locale) {
        payWayService.add(model, locale);
        return "wallet/topgate-recharge-save";
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop(value = "充值支付方式配置编辑页",monitor = false)
    @GetMapping(value = "/edit")
    @RequiresPermissions("edit:topgate:recharge:payway")
    public String edit(TopGateRechargePaywayFindOneReq saveReq, Model model, Locale locale) {
        payWayService.findOne(saveReq, model, locale);
        return "wallet/topgate-recharge-edit";
    }

    @SysLogAop(value = "充值支付方式配置保存操作", monitor = false)
    @PostMapping(value = "/save")
    @RequiresPermissions("topgate:recharge:payway:index")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody TopGateRechargePaywaySaveReq req) {
        return payWayService.save(req);
    }

    @SysLogAop("充值支付方式配置编辑列表数据")
    @PostMapping(value = "/list/page")
    @RequiresPermissions("topgate:recharge:payway:index")
    @ResponseBody
    public BaseResult listPage(TopGateRechargePaywayPageReq req,Locale locale) {
        return payWayService.page(req,locale);
    }

    @SysLogAop(value = "提现支付方式开关操作", monitor = false)
    @PostMapping(value = "/onOrOff")
    @RequiresPermissions("onOrOff:topgate:recharge:payway")
    @ResponseBody
    public BaseResult onOrOff(@Valid @RequestBody TopGateWithdrawPaywayOnOrOffReq req) {
        payWayService.updateEnable(req);
        return BaseResult.success();
    }

    @SysLogAop(value = "充值支付方式配置删除操作", monitor = false)
    @PostMapping(value = "/del")
    @RequiresPermissions("del:topgate:recharge:payway")
    @ResponseBody
    public BaseResult del(@Valid @RequestBody TopGateRechargePaywayRemoveReq req) {
        return payWayService.remove(req);
    }

    /**
     * 上传支付方式logo
     *
     * @param file
     * @return
     */
    @SysLogAop("上传支付方式logo")
    @PostMapping("/upload")
    @ResponseBody
    public BaseResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        return payWayService.upload(file);
    }
}

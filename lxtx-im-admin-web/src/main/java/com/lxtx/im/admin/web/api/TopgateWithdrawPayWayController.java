package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.TopgateWithdrawPayWayService;
import com.lxtx.im.admin.service.request.DictDeleteReq;
import com.lxtx.im.admin.service.request.DictInfoReq;
import com.lxtx.im.admin.service.request.DictListPageReq;
import com.lxtx.im.admin.service.request.DictSaveReq;
import com.lxtx.im.admin.service.response.DictInfoResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/topgate/withdraw/payway")
public class TopgateWithdrawPayWayController {

    @Autowired
    private TopgateWithdrawPayWayService payWayService;

    /**
     * topgate 提现支付方式配置首页
     */
    @SysLogAop("提现支付方式配置首页")
    @GetMapping("/index")
    @RequiresPermissions("topgate:withdraw:payway:index")
    public String index(Model model) {
        List<DictInfoResp> dictInfoResps = payWayService.selectListDict();
        model.addAttribute("topgateDicts", dictInfoResps);
        return "wallet/topgate-withdraw-index";
    }

    /**
     * 跳转到新增页面
     */
    @SysLogAop(value = "提现支付方式配置新增页", monitor = false)
    @GetMapping(value = "/add")
    @RequiresPermissions("add:topgate:withdraw:payway")
    public String add(Model model, Locale locale) {
        payWayService.add(model, locale);
        return "wallet/topgate-withdraw-save";
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop(value = "提现支付方式配置编辑页", monitor = false)
    @GetMapping(value = "/edit")
    @RequiresPermissions("edit:topgate:withdraw:payway")
    public String edit(TopGateWithdrawPaywayFindOneReq saveReq, Model model, Locale locale) {
        payWayService.selectOneWithdrawPayWay(saveReq, model, locale);
        return "wallet/topgate-withdraw-edit";
    }

    @SysLogAop(value = "提现支付方式配置保存操作", monitor = false)
    @PostMapping(value = "/save")
    @RequiresPermissions("topgate:withdraw:payway:index")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody TopGateWithdrawPaywaySaveReq req) {
        return payWayService.saveWithdrawPayWay(req);
    }

    @SysLogAop("提现支付方式配置编辑列表数据")
    @PostMapping(value = "/list/page")
    @RequiresPermissions("topgate:withdraw:payway:index")
    @ResponseBody
    public BaseResult listPage(TopGateWithdrawPaywayPageReq req, Locale locale) {
        return payWayService.pageWithdrawPayWay(req, locale);
    }

    @SysLogAop(value = "提现支付方式配置删除操作", monitor = false)
    @PostMapping(value = "/del")
    @RequiresPermissions("del:topgate:withdraw:payway")
    @ResponseBody
    public BaseResult del(@Valid @RequestBody TopGateWithdrawPaywayRemoveReq req) {
        return payWayService.removeWithdrawPayWay(req);
    }

    @SysLogAop(value = "提现支付方式开关操作", monitor = false)
    @PostMapping(value = "/onOrOff")
    @RequiresPermissions("onOrOff:topgate:withdraw:payway")
    @ResponseBody
    public BaseResult onOrOff(@Valid @RequestBody TopGateWithdrawPaywayOnOrOffReq req) {
        payWayService.updateEnableWithdrawPayWay(req);
        return BaseResult.success();
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

    @SysLogAop(value = "topgate页面字典删除操作", monitor = false)
    @PostMapping(value = "/delDict")
    @RequiresPermissions("delDict:topgate:withdraw:payway")
    @ResponseBody
    public BaseResult delDict(@Valid @RequestBody DictDeleteReq req) {
        payWayService.deleteDict(req);
        return BaseResult.success();
    }

    @SysLogAop(value = "topgate页面字典保存操作", monitor = false)
    @PostMapping(value = "/saveDict")
    @RequiresPermissions("topgate:withdraw:payway:index")
    @ResponseBody
    public BaseResult saveDict(@Valid @RequestBody DictSaveReq req) {
        return payWayService.saveOrUpdateDict(req);
    }

    @SysLogAop(value = "topgate页面字典列表操作", monitor = false)
    @PostMapping(value = "/dictPage")
    @RequiresPermissions("topgate:withdraw:payway:index")
    @ResponseBody
    public BaseResult dictPage(@Valid DictListPageReq req) {
        return payWayService.listPageDict(req);
    }

    @SysLogAop("topgate页面字典新增页")
    @GetMapping(value = "/addDict")
    @RequiresPermissions("addDict:topgate:withdraw:payway")
    public String addDict() {
        return "wallet/topgate-limit-save";
    }

    @SysLogAop("topgate页面字典修改页")
    @GetMapping(value = "/editDict")
    @RequiresPermissions("editDict:topgate:withdraw:payway")
    public String editDict(DictInfoReq req, Model model) {
        payWayService.setDictModel(req, model);
        return "wallet/topgate-limit-save";
    }
}

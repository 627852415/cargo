package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.PlatformWithdrawConfigService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.DictInfoResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统提现配置
 *
 * @author CaiRH
 * @since 2018-12-20
 */
@Controller
@RequestMapping("/platform/withdraw/config")
public class PlatformWithdrawConfigController {

    @Autowired
    private PlatformWithdrawConfigService platformWithdrawConfigService;

    @Autowired
    private DictService dictService;

    /**
     * 系统提现配置首页
     */
    @SysLogAop("系统提现配置首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("platform:withdraw:config:index")
    public String index() {
        return "platform/platform-withdraw-config-index";
    }

    /**
     * otc限额配置首页
     */
    @SysLogAop("otc限额配置首页")
    @GetMapping(value = "/otc/limit/index")
    @RequiresPermissions("platform:withdraw:config:otc:limit:index")
    public String otcLimitIndex() {
        return "otc/limit-amount-index";
    }

    /**
     * 系统提现配置新增或修改界面
     */
    @SysLogAop("系统提现配置新增或修改界面")
    @GetMapping(value = "/modifyPage")
    @RequiresPermissions("platform:withdraw:config:modifyPage")
    public String modifyPage(PlatformWithdrawConfigSelectReq req, Model model) {
        platformWithdrawConfigService.info(req, model);
        return "platform/platform-withdraw-config-save";
    }

    /**
     * 系统提现配置列表
     */
    @SysLogAop("系统提现配置列表")
    @PostMapping(value = "/list")
    @RequiresPermissions("platform:withdraw:config:index")
    @ResponseBody
    public BaseResult listPage(PlatformWithdrawConfigListReq req) {
        return platformWithdrawConfigService.listPage(req);
    }

    /**
     * 保存系统提款配置
     */
    @SysLogAop(value = "保存系统提款配置", monitor = true)
    @PostMapping(value = "/save")
    @RequiresPermissions("platform:withdraw:config:index")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody PlatformWithdrawConfigSaveReq req) {
        return platformWithdrawConfigService.save(req);
    }

    /**
     * 删除提款配置
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "删除提款配置", monitor = true)
    @PostMapping(value = "/delete")
    @RequiresPermissions("platform:withdraw:config:delete")
    @ResponseBody
    public BaseResult delete(@Valid @RequestBody PlatformWithdrawConfigDeleteReq req) {
        return platformWithdrawConfigService.delete(req);
    }

    /**
     * 获取otc每日限额
     *
     * @param req
     * @return
     */
    @SysLogAop("获取otc每日限额")
    @PostMapping(value = "/otc/limit")
    @RequiresPermissions("platform:withdraw:config:index")
    @ResponseBody
    public BaseResult list(OtcDailyLimitListPageReq req) {
        return platformWithdrawConfigService.limitListPage(req);
    }

    /**
     * 币种交易通知主页跳转
     *
     * @return
     */
    @SysLogAop("币种交易通知主页跳转")
    @GetMapping(value = "/otc/limit/edit")
    @RequiresPermissions("platform:withdraw:config:index")
    public String otcLimitEdit(DictInfoReq req, Model model) {
        BaseResult result = dictService.info(req);
        DictInfoResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), DictInfoResp.class);
        model.addAttribute("dict", resp);
        return "otc/limit-amount-edit";
    }

}

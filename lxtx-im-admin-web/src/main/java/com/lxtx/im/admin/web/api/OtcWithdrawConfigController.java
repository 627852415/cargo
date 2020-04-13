package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.OtcWithdrawConfigService;
import com.lxtx.im.admin.service.request.WithdrawConfigDeleteReq;
import com.lxtx.im.admin.service.request.WithdrawConfigPageReq;
import com.lxtx.im.admin.service.request.WithdrawConfigSaveOrUpdateReq;
import com.lxtx.im.admin.service.response.CoinResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Czh
 * Date: 2019-06-25 11:21
 */
@Controller
@RequestMapping("/otc/withdraw/config")
public class OtcWithdrawConfigController {

    @Autowired
    private OtcWithdrawConfigService otcWithdrawConfigService;

    @Autowired
    private CoinService coinService;

    /**
     * 功能描述: OTC提现配置
     *
     * @return:
     * @author: Czh
     * @date: 2019-06-25 11:27
     */
    @SysLogAop("OTC提现配置首页")
    @GetMapping("/index")
    @RequiresPermissions("otc:withdraw:config:index")
    public String index() {
        return "otc/withdraw-config-index";
    }

    /**
     * 功能描述: OTC提现配置新增或修改界面
     *
     * @param: id
     * @param: model
     * @return:
     * @author: Czh
     * @date: 2019-06-25 11:50
     */
    @SysLogAop("OTC提现配置新增或修改界面")
    @GetMapping(value = "/modifyPage")
    @RequiresPermissions("otc:withdraw:config:modifyPage")
    public String modifyPage(String id, Model model) {
        List<CoinResp> coinList = coinService.getAllCoinList();
        model.addAttribute("coins", coinList);
        model.addAttribute("config", otcWithdrawConfigService.selectById(id));
        return "otc/withdraw-config-save";
    }

    /**
     * 功能描述: 保存OTC提款配置
     * w
     *
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    @SysLogAop(value = "保存OTC提款配置", monitor = true)
    @PostMapping("/saveOrUpdate")
    @RequiresPermissions("otc:withdraw:config:index")
    @ResponseBody
    public BaseResult withdrawConfigSaveOrUpdate(@RequestBody @Valid WithdrawConfigSaveOrUpdateReq req) {
        return otcWithdrawConfigService.withdrawConfigSaveOrUpdate(req);
    }

    /**
     * 功能描述: 获取OTC提款配置
     *
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    @SysLogAop("获取OTC提款配置")
    @PostMapping("/listPage")
    @RequiresPermissions("otc:withdraw:config:index")
    @ResponseBody
    public BaseResult listPage(WithdrawConfigPageReq req) {
        return otcWithdrawConfigService.withdrawConfigListPage(req);
    }

    /**
     * 功能描述: 删除OTC提款配置
     *
     *
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    @SysLogAop(value = "删除OTC提款配置", monitor = true)
    @PostMapping("/delete")
    @RequiresPermissions("otc:withdraw:config:delete")
    @ResponseBody
    public BaseResult withdrawConfigDelete(@RequestBody WithdrawConfigDeleteReq req) {
        return otcWithdrawConfigService.withdrawConfigDelete(req);
    }

    /**
     * 功能描述: 启用OTC提款配置
     *
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    @SysLogAop(value = "启用OTC提款配置", monitor = true)
    @PostMapping("/enable")
    @RequiresPermissions("otc:withdraw:config:enable")
    @ResponseBody
    public BaseResult withdrawConfigEnable(@RequestBody WithdrawConfigDeleteReq req) {
        return otcWithdrawConfigService.withdrawConfigEnable(req);
    }

}
package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.CoinChargeService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.CoinChargeInfoResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @description: 币种手续费管理
 * @author: CXM
 * @create: 2018-10-12 12:04
 */
@Controller
@RequestMapping("/coinCharge")
public class CoinChargeController {

    @Autowired
    private CoinChargeService coinChargeService;
    @Autowired
    private AirdropService airdropService;

    /**
     * 币种手续费管理管理主页跳转
     *
     * @return
     */
    @SysLogAop("手续费管理管理主页跳转")
    @GetMapping(value = "/index")
    @RequiresPermissions("coinCharge:index")
    public String toIndex(Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        return "coinCharge/coin-charge-index";
    }

    /**
     * 币种手续费管理保存跳转页
     *
     * @return
     */
    @SysLogAop("手续费管理保存跳转页")
    @GetMapping(value = "/add")
    @RequiresPermissions("coinCharge:add")
    public String add() {
        return "coinCharge/coin-charge-save";
    }

    /**
     * 币种手续费管理管理列表
     *
     * @return
     */
    @SysLogAop("币种手续费管理管理列表")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("coinCharge:index")
    public BaseResult listPage(@Valid @RequestBody CoinChargeListPageReq req) {
        return coinChargeService.listPage(req);
    }

    /**
     * 根据id获取币种手续费管理信息
     *
     * @param req
     * @return
     */
    @SysLogAop("币种手续费管理信息")
    @GetMapping(value = "/info")
    @RequiresPermissions("coinCharge:index")
    public String info(CoinChargeInfoReq req, Model model) {
        BaseResult result = coinChargeService.info(req);
        CoinChargeInfoResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), CoinChargeInfoResp.class);
        model.addAttribute("coinCharge", resp);
        return "coinCharge/coin-charge-save";
    }

    /**
     * 保存或更新币种手续费管理
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/save")
    @ResponseBody
    @SysLogAop(value = "保存或更新币种手续费管理", monitor = true)
    @RequiresPermissions("coinCharge:index")
    public BaseResult save(@Valid @RequestBody CoinChargeSaveReq req) {
        return coinChargeService.saveOrUpdate(req);
    }

    /**
     * 保存或更新币种手续费管理
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/update")
    @ResponseBody
	@SysLogAop(value = "保存或更新币种手续费管理", monitor = true)
    @RequiresPermissions("coinCharge:index")
    public BaseResult update(@Valid @RequestBody CoinChargeSaveReq req) {
        return coinChargeService.saveOrUpdate(req);
    }

    /**
     * 删除币种手续费管理
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    @SysLogAop(value = "删除币种手续费", monitor = true)
    @RequiresPermissions("coinCharge:delete")
    public BaseResult delete(@Valid @RequestBody CoinChargeDeleteReq req) {
        return coinChargeService.delete(req);
    }

}

package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.WithdrawApplyFailService;
import com.lxtx.im.admin.service.enums.EnumSixTransferFailType;
import com.lxtx.im.admin.service.enums.EnumWithdrawApplyFailRecordStatus;
import com.lxtx.im.admin.service.request.AirdropToSavePageReq;
import com.lxtx.im.admin.service.request.WithdrawApplyDealFailReq;
import com.lxtx.im.admin.service.request.WithdrawApplyDealSuccessReq;
import com.lxtx.im.admin.service.request.WithdrawApplyFailReq;
import com.lxtx.im.admin.service.response.CoinResp;
import com.lxtx.im.admin.service.response.WithdrawApplyFailRecordStatusResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 提现申请失败管理
 */
@Controller
@RequestMapping("/withdraw/apply/fail")
public class WithdrawApplyFailController {

    @Autowired
    private WithdrawApplyFailService withdrawApplyFailService;
    @Autowired
    private AirdropService airdropService;

    @SysLogAop("提现申请失败管理首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("withdraw:apply:fail:index")
    public String index(Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));

            String coinStr = JSONObject.toJSONString(map.get("coins"));
            List<CoinResp> coins = JSONObject.parseArray(coinStr, CoinResp.class);
            Map<String, String> coinIdNameMap = coins.stream().collect(Collectors.toMap(CoinResp::getId, CoinResp::getCoinName));
            model.addAttribute("coinIdNameMap", JSONObject.toJSONString(coinIdNameMap));
        }
        List<WithdrawApplyFailRecordStatusResp> statusList = new ArrayList<>();
        List<WithdrawApplyFailRecordStatusResp> typeList = new ArrayList<>();
        Arrays.stream(EnumWithdrawApplyFailRecordStatus.values()).forEach(o -> {
            WithdrawApplyFailRecordStatusResp statusResp = new WithdrawApplyFailRecordStatusResp();
            statusResp.setCode(o.getCode());
            statusResp.setDescription(o.getDescription());
            statusList.add(statusResp);
        });
        Arrays.stream(EnumSixTransferFailType.values()).forEach(o -> {
            WithdrawApplyFailRecordStatusResp statusResp = new WithdrawApplyFailRecordStatusResp();
            statusResp.setCode(o.getCode());
            statusResp.setDescription(o.getDescription());
            typeList.add(statusResp);
        });
        model.addAttribute("statusMap", JSONObject.toJSONString(statusList));
        model.addAttribute("typeMap", JSONObject.toJSONString(typeList));
        return "wallet/withdraw-fail-index";
    }

    /**
     * 列表数据
     *
     * @param withdrawApplyFailReq
     * @return
     */
    @SysLogAop("提现申请失败列表数据")
    @PostMapping(value = "/list")
    @RequiresPermissions("withdraw:apply:fail:index")
    @ResponseBody
    public BaseResult list(WithdrawApplyFailReq withdrawApplyFailReq) {
        BaseResult result = withdrawApplyFailService.listPage(withdrawApplyFailReq);
        return result;
    }

    /**
     * 处理失败
     *
     * @param withdrawApplyDealFailReq
     * @return
     */
    @SysLogAop(value = "提现申请失败处理失败操作", monitor = true)
    @PostMapping(value = "/dealFail")
    @RequiresPermissions("withdraw:apply:fail:index")
    @ResponseBody
    public BaseResult doFail(@RequestBody WithdrawApplyDealFailReq withdrawApplyDealFailReq) {
        BaseResult result = withdrawApplyFailService.doFail(withdrawApplyDealFailReq);
        return result;
    }

    /**
     * 处理成功
     *
     * @param withdrawApplyDealSuccessReq
     * @return
     */
    @SysLogAop(value = "提现申请失败处理成功操作", monitor = true)
    @PostMapping(value = "/dealSuccess")
    @RequiresPermissions("withdraw:apply:fail:index")
    @ResponseBody
    public BaseResult doSuccess(@RequestBody WithdrawApplyDealSuccessReq withdrawApplyDealSuccessReq) {
        BaseResult result = withdrawApplyFailService.doSuccess(withdrawApplyDealSuccessReq);
        return result;
    }
}

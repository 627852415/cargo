package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.OffsiteExchangeFundAccountService;
import com.lxtx.im.admin.service.request.AirdropToSavePageReq;
import com.lxtx.im.admin.service.request.StatisticsOrderReq;
import com.lxtx.im.admin.service.response.OffsiteExchangeFundAcountResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Description: 资金账号统计
 * @author: ZhangZhongWu
 * @date: 2019/6/10 14:44
 */
@Controller
@RequestMapping("/offsite/exchange/fundAccount/statistics")
public class OffsiteExchangeFundAccountController {

    @Resource
    private OffsiteExchangeFundAccountService offsiteExchangefundAccountService;

    @Autowired
    private AirdropService airdropService;

    /**
     * 获取资金池账号信息
     *
     * @param model
     * @return
     */
    @SysLogAop("获取资金池账号信息")
    @GetMapping("/index")
    @RequiresPermissions("offsite:exchange:fundAccount:statistics:index")
    public String toIndex(Model model) {
        OffsiteExchangeFundAcountResp result = offsiteExchangefundAccountService.getFundAccountInfo();
        BaseResult airdropResult = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (airdropResult.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) airdropResult.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        model.addAttribute("accountInfo", result);
        return "offsiteExchangeFundAccount/statistics-index";
    }

    /**
     * 根据日期订单统计
     *
     * @param statisticsOrderReq
     * @return
     */
    @SysLogAop("根据日期订单统计")
    @PostMapping(value = "/get/order")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:fundAccount:statistics:index")
    public BaseResult getOrder(@Valid @RequestBody StatisticsOrderReq statisticsOrderReq) {
        return BaseResult.success(offsiteExchangefundAccountService.getFundAccountInfoByDate(statisticsOrderReq));
    }
}

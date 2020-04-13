package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.StatisticsService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @Author tangdy
 * @Date 2018-09-10
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private AirdropService airdropService;

    /**
     * 资产统计
     * @return
     */
    @SysLogAop("资产统计首页")
    @GetMapping(value = "/coin/index")
    @RequiresPermissions("statistics:coin:index")
    public String index() {
        return "admin/statistics-coin-index";
    }

    /**
     * 资产统计列表（不含商户资产）
     * @return
     */
    @SysLogAop("资产统计列表（不含商户资产）")
    @PostMapping(value = "/coin/list")
    @RequiresPermissions("statistics:coin:index")
    @ResponseBody
    public BaseResult list(@RequestBody StatisticsCoinReq req) {
        return statisticsService.listCoin(req);
    }

    /**
     * 资产统计列表（含商户资产）
     * @param req
     * @return
     */
    @SysLogAop("资产统计列表（含商户资产）")
    @PostMapping(value = "/coin/list/contain/merchant/assets")
    @RequiresPermissions("statistics:coin:index")
    @ResponseBody
    public BaseResult coinListContainMerchantAssets(@RequestBody StatisticsCoinContainMerchantAssetsReq req) {
        return statisticsService.listCoinContainMerchantAssets(req);
    }

    @SysLogAop("资产统计数据")
    @PostMapping(value = "/coin/redoStatistics")
    @RequiresPermissions("statistics:coin:index")
    @ResponseBody
    public BaseResult redoStatistics(StatisticsCoinReq req) {
        return statisticsService.redoStatistics(req);
    }

    /**
     * 资产统计
     * @return
     */
    @SysLogAop("币种资产统计下载")
    @GetMapping(value = "/download")
    @RequiresPermissions("statistics:coin:index")
    public void download(HttpServletResponse response, StatisticsDownloadReq req) {
        statisticsService.download(response, req);
    }

    /**
     * 币种手续费统计跳转
     */
    @SysLogAop("币种手续费统计跳转")
    @GetMapping(value = "/coinCharge/index")
    @RequiresPermissions("statistics:coin:index")
    public String coinChargeIndex(Model model, String coinId) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        if(StringUtils.isNotBlank(coinId)){
            model.addAttribute("coinId", coinId);
        }

        return "coinCharge/statistics-coin-charge-index";
    }


    /**
     *  同步余额宝转出手续费旧数据
     */
    @SysLogAop(value = "同步余额宝转出手续费旧数据", monitor = true)
    @GetMapping(value = "/syncOldYebOutWithdrawFee")
    @RequiresPermissions("statistics:coin:syncOldYebOutWithdrawFee")
    @ResponseBody
    public BaseResult syncOldYebOutWithdrawFee() {
        BaseResult result = statisticsService.syncOldYebOutWithdrawFee();
        return result;
    }

    /**
     * 币种手续费统计
     */
    @SysLogAop("币种手续费统计")
    @PostMapping(value = "/coinCharge/list")
    @RequiresPermissions("statistics:coin:index")
    @ResponseBody
    public BaseResult coinChargeList(StatisticsCoinChargeListReq req) {
        return statisticsService.coinChargeList(req);
    }

    /**
     * 币种手续费列表
     */
    @SysLogAop("币种手续费列表")
    @PostMapping(value = "/coinCharge/listPage")
    @RequiresPermissions("statistics:coin:index")
    @ResponseBody
    public BaseResult coinChargeListPage(StatisticsCoinChargeListPageReq req) {
        return statisticsService.coinChargeListPage(req);
    }

    /**
     * 手续费统计下载
     * @return
     */
    @SysLogAop(value = "手续费统计下载", monitor = true)
    @GetMapping(value = "/coinCharge/list/download")
    @RequiresPermissions("statistics:coin:index")
    public void coinChargeDownload(HttpServletResponse response, StatisticsCoinChargeListReq req) {
        statisticsService.coinChargeDownload(response, req);
    }

    /**
     * 手续费列表下载
     * @return
     */
    @SysLogAop(value = "手续费列表下载", monitor = true)
    @RequestMapping(value = "/coinCharge/listPage/download")
    @RequiresPermissions("statistics:coin:index")
    public void coinChargeListPageDownload(HttpServletResponse response, StatisticsCoinChargeListPageReq req) {
        statisticsService.coinChargeListPageDownload(response, req);
    }
}

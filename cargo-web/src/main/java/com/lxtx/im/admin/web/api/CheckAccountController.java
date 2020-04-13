package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.Coin;
import com.lxtx.im.admin.service.CheckAccountService;
import com.lxtx.im.admin.service.request.PlatformAllCoinCheckTimeReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/check")
public class CheckAccountController {

    @Autowired
    private CheckAccountService checkAccountService;

    /**
     * 跳转到统计首页
     *
     * @return
     */
    @SysLogAop("统计首页")
    @RequestMapping("/checkIndex")
    @RequiresPermissions("check:index")
    public String checkIndex(Model model) {
        List<Coin> allCoin = checkAccountService.getAllCoin();
        model.addAttribute("allCoin", allCoin);
        return "checkaccount/checkindex";
    }

    /**
     * 跳转到详情页（显示每个用户的交易流水）
     *
     * @return
     */
    @SysLogAop("用户的交易流水")
    @RequestMapping("/forwordDetail")
    @RequiresPermissions("check:index")
    public String forwordDetail(HttpServletRequest request, Model model) {
        String checkTime = request.getParameter("checkTime");
        String coinId = request.getParameter("coinId");
        String userId = request.getParameter("userId");
        model.addAttribute("checkTime", checkTime);
        model.addAttribute("coinId", coinId);
        model.addAttribute("userId", userId);
        return "checkaccount/checkflowdetail";
    }

    /**
     * 取得平台某天各币种下的资产总额
     *
     * @param platformAllCoinAccount
     * @return
     */
    @SysLogAop("取得平台某天各币种下的资产总额")
    @RequestMapping("/getPlatformlCoinBatchDetail")
    @ResponseBody
    @RequiresPermissions("check:index")
    public BaseResult getPlatformlCoinBatchDetail(@RequestBody PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        return checkAccountService.getPlatformlCoinBatchDetail(platformAllCoinAccount);
    }

    /**
     * 取得平台某天某币种差错记录
     *
     * @param platformAllCoinAccount
     * @return
     */
    @SysLogAop("取得平台某天某币种差错记录")
    @RequestMapping("/getCoinMistakeRecords")
    @ResponseBody
    @RequiresPermissions("check:index")
    public BaseResult getCoinMistakeRecords(@RequestBody PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        BaseResult coinMistakeRecords = checkAccountService.getCoinMistakeRecords(platformAllCoinAccount);
        return coinMistakeRecords;
    }

    /**
     * 取得平台某币种某天的差错总金额和差错笔数
     *
     * @param platformAllCoinAccount
     * @return
     */
    @SysLogAop("取得平台某币种某天的差错总金额和差错笔数")
    @RequestMapping("/getMistakeAmountAndMistakeCount")
    @ResponseBody
    @RequiresPermissions("check:index")
    public BaseResult getMistakeAmountAndMistakeCount(@RequestBody PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        return checkAccountService.getMistakeAmountAndMistakeCount(platformAllCoinAccount);
    }

    /**
     * 取得趋势图数据
     */
    @SysLogAop("取得趋势图数据")
    @RequestMapping("/getTrendData")
    @ResponseBody
    @RequiresPermissions("check:index")
    public BaseResult getTrendData(@RequestBody PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        return checkAccountService.getTrendData(platformAllCoinAccount);
    }

    /**
     * 取得交易记录详情
     *
     * @param platformAllCoinAccount
     * @return
     */
    @SysLogAop("取得交易记录详情")
    @RequestMapping("/getFlowDetail")
    @ResponseBody
    @RequiresPermissions("check:index")
    public BaseResult getFlowDetail(@RequestBody PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        BaseResult flowDetail = checkAccountService.getFlowDetail(platformAllCoinAccount);
        return flowDetail;
    }

    /**
     * 根据币种ID和对账时间取得平台当天总资产趋势图数据
     *
     * @param platformAllCoinAccount
     * @return
     */
    @SysLogAop("根据币种ID和对账时间取得平台当天总资产趋势图数据")
    @RequestMapping("/data/total")
    @ResponseBody
    @RequiresPermissions("check:index")
    public BaseResult getChartTotalData(@RequestBody PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        return checkAccountService.getChartTotalData(platformAllCoinAccount);
    }

    /**
     * 根据币种ID和对账时间取得币种当天交易额趋势图数据，分交易类型显示多天线
     *
     * @param platformAllCoinAccount
     * @return
     */
    @SysLogAop("根据币种ID和对账时间取得币种当天交易额趋势图数据")
    @RequestMapping("/data/currency")
    @ResponseBody
    @RequiresPermissions("check:index")
    public BaseResult getChartCurrencyData(@RequestBody PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        return checkAccountService.getChartCurrencyData(platformAllCoinAccount);
    }

    /**
     * 根据币种和对账时间取得币种当天差异额趋势图数据
     *
     * @param platformAllCoinAccount
     * @return
     */
    @SysLogAop("根据币种和对账时间取得币种当天差异额趋势图数据")
    @RequestMapping("/data/diff")
    @ResponseBody
    @RequiresPermissions("check:index")
    public BaseResult getChartDiffData(@RequestBody PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        return checkAccountService.getChartDiffData(platformAllCoinAccount);
    }
}

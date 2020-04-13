package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.TopgateWithdrawPayWayService;
import com.lxtx.im.admin.service.TradingRecordWithdrawTopgateService;
import com.lxtx.im.admin.service.request.TradingRecordWithdrawTopgateDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordWithdrawTopgateReq;
import com.lxtx.im.admin.service.response.RechargeTopgateOrderStatisticsResp;
import com.lxtx.im.admin.service.response.WithdrawTopgateOrderStatisticsResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Topgate闪兑订单
 *
 * @author hechizhi
 * @since 2020-1-3
 */
@Controller
@RequestMapping("/tradingRecord/withdrawTopgate")
public class TradingRecordWithdrawTopgateController {

    @Autowired
    private TradingRecordWithdrawTopgateService tradingRecordWithdrawTopgateService;
    @Autowired
    private TopgateWithdrawPayWayService topgateWithdrawPayWayService;

    /**
     * Topgate提现订单列表
     *
     * @return
     */
    @SysLogAop("跳转提现订单列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("transaction:withdraw:index")
    public String toIndex(boolean refresh,Model model) {
        WithdrawTopgateOrderStatisticsResp resp = tradingRecordWithdrawTopgateService.getAllStatistics(refresh);
        model.addAttribute("totalStatistics",resp);
        return "wallet/tradingRecord-withdrawTopgate-index";
    }


    /**
     * Topgate提现订单列表数据
     *
     * @param tradingRecordWithdrawTopgateReq
     * @param session
     * @return
     */
    @SysLogAop("查询Topgate提现订单列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:withdraw:index")
    public BaseResult listPage(TradingRecordWithdrawTopgateReq tradingRecordWithdrawTopgateReq, HttpSession session, Locale locale) {
        return tradingRecordWithdrawTopgateService.listPage(tradingRecordWithdrawTopgateReq, session, locale);
    }
    
    /**
     * 
     * @Description 获取Topgate提现订单记录列表导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取Topgate提现订单记录导出锁")
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:withdraw:index")
    public BaseResult downloadLockFriends(TradingRecordWithdrawTopgateReq req, HttpSession session, Locale locale) {
    	return tradingRecordWithdrawTopgateService.downloadLock(req,session, locale);
    }


    /**
     * 列表导出
     */
    @SysLogAop(value = "Topgate提现订单列表导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("transaction:withdraw:index")
    public void downloadList(HttpServletResponse response, HttpSession session, TradingRecordWithdrawTopgateReq tradingRecordWithdrawTopgateReq, Locale locale) {
        tradingRecordWithdrawTopgateService.downloadList(response, session, tradingRecordWithdrawTopgateReq,locale);
    }

    /**
     * 查看详情
     *
     * @param tradingRecordWithdrawTopgateDetailReq
     * @param model
     * @return
     */
    @SysLogAop("查询Topgate提现订单详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("transaction:withdraw:index")
    public String detail(TradingRecordWithdrawTopgateDetailReq tradingRecordWithdrawTopgateDetailReq, Model model, Locale locale) {
        return tradingRecordWithdrawTopgateService.detail(tradingRecordWithdrawTopgateDetailReq, model,locale);
    }

    /**
     * 获取全部提取方法
     *
     * @return
     */
    @SysLogAop("获取全部提取方法")
    @GetMapping(value = "/palway/list")
    @ResponseBody
    @RequiresPermissions("transaction:withdraw:index")
    public BaseResult list(Locale locale) {
        return topgateWithdrawPayWayService.listAll(locale);
    }
}


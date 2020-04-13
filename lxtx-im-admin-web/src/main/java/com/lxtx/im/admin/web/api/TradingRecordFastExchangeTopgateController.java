package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.TradingRecordFastExchangeService;
import com.lxtx.im.admin.service.TradingRecordFastExchangeTopgateService;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeReq;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeTopgateDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeTopgateReq;
import com.lxtx.im.admin.service.response.FastExchangeTopgateOrderStatisticsResp;
import com.lxtx.im.admin.service.response.RechargeTopgateOrderStatisticsResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/tradingRecord/fastExchangeTopgate")
public class TradingRecordFastExchangeTopgateController {

    @Autowired
    private TradingRecordFastExchangeTopgateService tradingRecordFastExchangeTopgateService;

    /**
     * 闪兑订单列表
     *
     * @return
     */
    @SysLogAop("跳转topgate闪兑订单列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("transaction:fast:exchange:topgate:index")
    public String toIndex(boolean refresh, Model model) {
        FastExchangeTopgateOrderStatisticsResp resp = tradingRecordFastExchangeTopgateService.getAllStatistics(refresh);
        model.addAttribute("totalStatistics",resp);
        return "wallet/tradingRecord-fastExchangeTopgate-index";
    }


    /**
     * 闪兑订单列表数据
     *
     * @param tradingRecordFastExchangeTopgateReq
     * @param session
     * @return
     */
    @SysLogAop("查询topgate闪兑订单列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:fast:exchange:topgate:index")
    public BaseResult listPage(TradingRecordFastExchangeTopgateReq tradingRecordFastExchangeTopgateReq, HttpSession session, Locale locale) {
        return tradingRecordFastExchangeTopgateService.listPage(tradingRecordFastExchangeTopgateReq, session, locale);
    }
    
    /**
     * 
     * @Description 获取topgate闪兑订单列表导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取topgate闪兑订单记录导出锁")
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:fast:exchange:topgate:index")
    public BaseResult downloadLockFriends(TradingRecordFastExchangeTopgateReq req, HttpSession session, Locale locale) {
    	return tradingRecordFastExchangeTopgateService.downloadLock(req,session, locale);
    }


    /**
     * 列表导出
     */
    @SysLogAop(value = "topgate闪兑订单记录列表导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("transaction:fast:exchange:topgate:index")
    public void downloadList(HttpServletResponse response, HttpSession session, TradingRecordFastExchangeTopgateReq tradingRecordFastExchangeTopgateReq, Locale locale) {
        tradingRecordFastExchangeTopgateService.downloadList(response, session, tradingRecordFastExchangeTopgateReq, locale);
    }

    /**
     * 查看详情
     *
     * @param tradingRecordFastExchangeTopgateDetailReq
     * @param model
     * @return
     */
    @SysLogAop("查询topgate闪兑订单详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("transaction:fast:exchange:topgate:index")
    public String detail(TradingRecordFastExchangeTopgateDetailReq tradingRecordFastExchangeTopgateDetailReq, Model model, Locale locale) {
        return tradingRecordFastExchangeTopgateService.detail(tradingRecordFastExchangeTopgateDetailReq, model, locale);
    }
}


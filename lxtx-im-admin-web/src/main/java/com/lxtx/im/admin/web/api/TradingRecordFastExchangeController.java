package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.TradingRecordFastExchangeService;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeReq;
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

/**
 * 闪兑交易流水
 *
 * @author xufeifei
 * @since 2019-11-23
 */
@Controller
@RequestMapping("/tradingRecord/fastExchange")
public class TradingRecordFastExchangeController {

    @Autowired
    private TradingRecordFastExchangeService tradingRecordFastExchangeService;

    /**
     * 闪兑交易流水列表
     *
     * @return
     */
    @SysLogAop("跳转闪兑交易流水列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("transaction:fast:exchange:index")
    public String toIndex() {
        return "wallet/tradingRecord-fastExchange-index";
    }


    /**
     * 闪兑交易流水列表数据
     *
     * @param tradingRecordFastExchangeReq
     * @param session
     * @return
     */
    @SysLogAop("查询闪兑交易流水列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:fast:exchange:index")
    public BaseResult listPage(TradingRecordFastExchangeReq tradingRecordFastExchangeReq, HttpSession session) {
        return tradingRecordFastExchangeService.listPage(tradingRecordFastExchangeReq, session);
    }
    
    /**
     * 
     * @Description 获取OTC交易记录列表导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取OTC交易记录导出锁")
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:fast:exchange:index")
    public BaseResult downloadLockFriends(TradingRecordFastExchangeReq req, HttpSession session) {
    	return tradingRecordFastExchangeService.downloadLock(req,session);
    }


    /**
     * 列表导出
     */
    @SysLogAop(value = "闪兑交易记录列表导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("transaction:fast:exchange:index")
    public void downloadList(HttpServletResponse response, HttpSession session, TradingRecordFastExchangeReq tradingRecordFastExchangeReq) {
        tradingRecordFastExchangeService.downloadList(response, session, tradingRecordFastExchangeReq);
    }

    /**
     * 查看详情
     *
     * @param tradingRecordFastExchangeDetailReq
     * @param model
     * @return
     */
    @SysLogAop("查询交易流水详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("transaction:fast:exchange:index")
    public String detail(TradingRecordFastExchangeDetailReq tradingRecordFastExchangeDetailReq, Model model) {
        return tradingRecordFastExchangeService.detail(tradingRecordFastExchangeDetailReq, model);
    }
}


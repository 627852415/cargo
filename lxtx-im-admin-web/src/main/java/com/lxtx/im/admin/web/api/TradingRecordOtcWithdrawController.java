package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.TradingRecordOtcWithdrawService;
import com.lxtx.im.admin.service.request.*;
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

@Controller
@RequestMapping("/tradingRecord/otcWithdraw")
public class TradingRecordOtcWithdrawController {

    //OTC交易记录
    @Autowired
    private TradingRecordOtcWithdrawService tradingRecordOtcWithdrawService;

    /**
     * 交易流水列表
     *
     * @return
     */
    @SysLogAop("跳转交易流水列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("transaction:otc:withdraw:index")
    public String toIndex() {
        return "wallet/tradingRecord-otcWithdraw-index";
    }


    /**
     * OTC交易记录查询
     *
     * @param tradingRecordOtcWithdrawReq
     * @param session
     * @return
     */
    @SysLogAop("查询交易流水列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:otc:withdraw:index")
    public BaseResult listPage(TradingRecordOtcWithdrawReq tradingRecordOtcWithdrawReq, HttpSession session) {
        return tradingRecordOtcWithdrawService.listPage(tradingRecordOtcWithdrawReq, session);
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
    @RequiresPermissions("transaction:otc:withdraw:index")
    public BaseResult downloadLockFriends(TradingRecordOtcWithdrawReq req, HttpSession session) {
    	return tradingRecordOtcWithdrawService.downloadLock(req,session);
    }

    /**
     * 列表导出
     */
    @SysLogAop(value = "交易记录列表导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("transaction:otc:withdraw:index")
    public void downloadList(HttpServletResponse response, HttpSession session, TradingRecordOtcWithdrawReq tradingRecordOtcWithdrawReq) {
        tradingRecordOtcWithdrawService.downloadList(response, session, tradingRecordOtcWithdrawReq);
    }

    /**
     * 查看详情
     *
     * @param tradingRecordOtcWithdrawDetailReq
     * @param model
     * @return
     */
    @SysLogAop("查询交易流水详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("transaction:otc:withdraw:index")
    public String detail(TradingRecordOtcWithdrawDetailReq tradingRecordOtcWithdrawDetailReq, Model model) {
        return tradingRecordOtcWithdrawService.detail(tradingRecordOtcWithdrawDetailReq, model);
    }
}
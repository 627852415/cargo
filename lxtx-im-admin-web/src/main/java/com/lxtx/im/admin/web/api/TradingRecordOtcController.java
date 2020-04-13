package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.TradingRecordOtcService;
import com.lxtx.im.admin.service.request.TradingRecordOtcDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordOtcReq;
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
@RequestMapping("/tradingRecord/otcRecharge")
public class TradingRecordOtcController {

    //OTC交易记录
    @Autowired
    private TradingRecordOtcService tradingRecordOTCService;

    /**
     * 交易流水列表
     *
     * @return
     */
    @SysLogAop("跳转交易流水列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("transaction:otc:recharge:index")
    public String toIndex() {
        return "wallet/tradingRecord-otc-index";
    }


    /**
     * OTC交易记录查询
     *
     * @param tradingRecordOtcReq
     * @param session
     * @return
     */
    @SysLogAop("查询交易流水列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:otc:recharge:index")
    public BaseResult listPage(TradingRecordOtcReq tradingRecordOtcReq, HttpSession session) {
        return tradingRecordOTCService.listPage(tradingRecordOtcReq, session);
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
    @RequiresPermissions("transaction:otc:recharge:index")
    public BaseResult downloadLockFriends(TradingRecordOtcReq req, HttpSession session) {
    	return tradingRecordOTCService.downloadLock(req,session);
    }


    /**
     * 列表导出
     */
    @SysLogAop(value = "交易记录列表导出")
    @GetMapping(value = "/download/list")
    @RequiresPermissions("transaction:otc:recharge:index")
    public void downloadList(HttpServletResponse response, HttpSession session, TradingRecordOtcReq tradingRecordOtcReq) {
        tradingRecordOTCService.downloadList(response, session, tradingRecordOtcReq);
    }

    /**
     * 查看详情
     *
     * @param tradingRecordOtcDetailReq
     * @param model
     * @return
     */
    @SysLogAop("查询交易流水详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("transaction:otc:recharge:index")
    public String detail(TradingRecordOtcDetailReq tradingRecordOtcDetailReq, Model model) {
        return tradingRecordOTCService.detail(tradingRecordOtcDetailReq, model);
    }
}

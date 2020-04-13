package com.lxtx.im.admin.web.api.transaction;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.TransactionDetailReq;
import com.lxtx.im.admin.service.request.TransactionRechargeExcelOutputReq;
import com.lxtx.im.admin.service.request.TransactionRechargePageReq;
import com.lxtx.im.admin.service.transaction.RechargeService;
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
 * 资金入账
 *
 * @author CaiRH
 * @since 2019-11-22
 */
@Controller
@RequestMapping("/transaction/recharge")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    /**
     * 资金入账列表
     *
     * @return
     */
    @SysLogAop("跳转资金入账列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("transaction:recharge:index")
    public String toIndex() {
        return "transaction/recharge-index";
    }


    /**
     * 资金入账列表数据
     *
     * @param req
     * @param session
     * @return
     */
    @SysLogAop("查询资金入账列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:recharge:index")
    public BaseResult listPage(TransactionRechargePageReq req, HttpSession session) {
        return rechargeService.listPage(req, session);
    }

    /**
     * 获取资金入账操作导出锁
     *
     * @return
     */
    @SysLogAop(value = "资金入账列表导出锁")
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:recharge:index")
    public BaseResult downloadLock(HttpSession session ,TransactionRechargePageReq req) {
        return rechargeService.downloadLock(session, req);
    }

    /**
     * 列表导出
     */
    @SysLogAop(value = "资金入账列表导出")
    @GetMapping(value = "/download/list")
    @RequiresPermissions("transaction:recharge:index")
    public void downloadList(HttpServletResponse response, HttpSession session, TransactionRechargeExcelOutputReq req) {
        rechargeService.downloadList(response, session, req);
    }

    /**
     * 查看详情
     *
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("查询资金入账详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("transaction:recharge:index")
    public String detail(TransactionDetailReq req, Model model) {
        return rechargeService.detail(req, model);
    }
}



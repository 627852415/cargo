package com.lxtx.im.admin.web.api.transaction;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxtx.im.admin.service.request.StatisticsOrderReq;
import com.lxtx.im.admin.service.response.RechargeTopgateOrderStatisticsResp;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.RechargeTopgateOrderService;
import com.lxtx.im.admin.service.request.RechargeTopgateOrderDetailReq;
import com.lxtx.im.admin.service.request.RechargeTopgateOrderDownloadReq;
import com.lxtx.im.admin.service.request.RechargeTopgateOrderPageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-12-17 15:36
 * @Description
 */
@Controller
@RequestMapping("/transaction/zbpay/order/")
public class RechargeTopgateOrderController {
    @Autowired
    private RechargeTopgateOrderService zbpayOrderService;
    /**
     * 理财宝订单列表
     *
     * @return
     */
    @SysLogAop("Topgate订单列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("transaction:zbpay:order:index")
    public String toIndex(boolean refresh, Model model) {
        RechargeTopgateOrderStatisticsResp resp = zbpayOrderService.getAllStatistics(refresh);
        model.addAttribute("totalStatistics",resp);
        return "transaction/zbpay-order-index";
    }


    /**
     * 理财宝订单列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("Topgate订单列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:zbpay:order:index")
    public BaseResult listPage(RechargeTopgateOrderPageReq req) {
        return zbpayOrderService.listPage(req);
    }


    /**
     * 理财宝订单列表导出
     */
    @SysLogAop(value = "Topgate订单列表导出", monitor = true)
    @GetMapping(value = "/download")
    @RequiresPermissions("transaction:zbpay:order:index")
    public void downloadList(HttpServletResponse response, RechargeTopgateOrderDownloadReq req, HttpSession session) {
        zbpayOrderService.downloadList(response, req,session);
    }

    /**
     * 查看详情
     *
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("查询Topgate订单详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("transaction:zbpay:order:index")
    public String detail(RechargeTopgateOrderDetailReq req, Model model) {
        return zbpayOrderService.detail(req, model);
    }

    /**
     *
     * @Description 获取好友转账列表导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取Topgate订单列表导出锁", monitor = true)
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:zbpay:order:index")
    public BaseResult downloadLock(RechargeTopgateOrderPageReq req, HttpSession session) {
        return zbpayOrderService.downloadLock(req,session);
    }
}

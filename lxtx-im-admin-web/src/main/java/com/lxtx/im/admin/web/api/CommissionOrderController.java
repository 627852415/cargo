package com.lxtx.im.admin.web.api;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CommissionOrderService;
import com.lxtx.im.admin.service.request.CommissionOrderDetailReq;
import com.lxtx.im.admin.service.request.CommissionOrderListReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 分佣订单管理 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-03-09
 */
@Controller
@RequestMapping("/commission/order")
public class CommissionOrderController {

    @Autowired
    private CommissionOrderService commissionOrderService;

    /**
     * 返佣订单列表
     *
     * @return
     */
    @SysLogAop("跳转返佣订单列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("commission:order:index")
    public String toIndex() {
        return "commissionOrder/commission-order-index";
    }

    @PostMapping("/list")
    @ResponseBody
    public BaseResult orderList(CommissionOrderListReq req) {
        return commissionOrderService.orderList(req);
    }

    /**
     * 跳转到详情页面
     */
    @SysLogAop("跳转订单详情")
    @GetMapping(value = "/toDetail")
    public String toDetail(CommissionOrderDetailReq req, Model model) {
        model.addAttribute("id",req.getId());
        if(req.getOrderType().equals("1")){
            model.addAttribute("orderType","撮合交易");
        }else if(req.getOrderType().equals("2")){
            model.addAttribute("orderType","理财宝");
        }else if(req.getOrderType().equals("3")){
            model.addAttribute("orderType","提现");
        }else if(req.getOrderType().equals("4")){
            model.addAttribute("orderType","支付");
        }else if(req.getOrderType().equals("5")){
            model.addAttribute("orderType","购买伙伴");
        }

        model.addAttribute("orderId",req.getOrderId());
        model.addAttribute("orderUserId",req.getOrderUserId());
        model.addAttribute("orderAmount",req.getOrderAmount());
        return "commissionOrder/order-detail";
    }

    /**
     * 跳转到详情页面
     */
    @SysLogAop("订单详情")
    @PostMapping(value = "/detail")
    @ResponseBody
    public BaseResult detail(CommissionOrderDetailReq req) {
        return commissionOrderService.detail(req);
    }

    /**
     *
     * @Description 获取返佣订单列表导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取订单记录导出锁")
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("commission:order:index")
    public BaseResult downloadLock(CommissionOrderListReq req, HttpSession session) {
        return commissionOrderService.downloadLock(req,session);
    }

    /**
     * 订单列表导出
     */
    @SysLogAop(value = "订单记录列表导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("commission:order:index")
    public void downloadList(HttpServletResponse response, HttpSession session, CommissionOrderListReq req) {
        commissionOrderService.downloadList(response, session, req);
    }
}
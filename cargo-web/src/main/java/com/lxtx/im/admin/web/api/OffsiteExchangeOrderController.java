package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.OffsiteExchangeOrderService;
import com.lxtx.im.admin.service.enums.EnumOffsiteExchangeOrderStatus;
import com.lxtx.im.admin.service.request.OffisteExchangeOrderEndReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeOrderDetailReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeOrderListPageReq;
import com.lxtx.im.admin.service.response.OffsiteExchangeOrderManageDetail;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
* @description:  线下汇换订单管理
* @author:   CXM
* @create:   2019-04-22 11:55
*/
@Controller
@RequestMapping("/offsite/exchange/order")
public class OffsiteExchangeOrderController {

    @Autowired
    private OffsiteExchangeOrderService offsiteExchangeOrderService;

    /**
     * 跳转订单主页
     *
     * @return
     */
    @SysLogAop("换汇跳转订单主页")
    @GetMapping(value = "/index")
    @RequiresPermissions("offsite:exchange:order:index")
    public String toIndex(Model model) {
        offsiteExchangeOrderService.waveRateList(model);
        return "offsiteExchangeOrder/offsite-exchange-order-index";
    }

    /**
     * 订单列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("换汇订单列表数据")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:order:index")
    public BaseResult list(OffsiteExchangeOrderListPageReq req) {
        return BaseResult.success(offsiteExchangeOrderService.listPage(req));
    }

    /**
     * 订单列表下载
     *
     * @return
     */
    @SysLogAop(value = "换汇订单列表下载", monitor = true)
    @RequestMapping(value = "/download")
    @RequiresPermissions("offsite:exchange:order:download")
    public void orderDownload(HttpServletResponse response, OffsiteExchangeOrderListPageReq req) {
        offsiteExchangeOrderService.orderDownload(response, req);
    }

    /**
     * 订单详情
     *
     * @return
     */
    @SysLogAop("换汇订单详情")
    @RequestMapping(value = "/detail")
    @RequiresPermissions("offsite:exchange:order:index")
    public String detail(OffsiteExchangeOrderDetailReq req, Model model) {
        OffsiteExchangeOrderManageDetail detail = offsiteExchangeOrderService.detail(req);
        model.addAttribute("detail", detail);
        if (EnumOffsiteExchangeOrderStatus.UNPAID.getCode().equals(detail.getStatus())) {
            return "offsiteExchangeOrder/offsite-exchange-order-unpaid-detail";
        } else if(EnumOffsiteExchangeOrderStatus.COMPLETED.getCode().equals(detail.getStatus())) {
            return "offsiteExchangeOrder/offsite-exchange-order-completed-detail";
        } else if (EnumOffsiteExchangeOrderStatus.CANCELLED.getCode().equals(detail.getStatus())) {
           return "offsiteExchangeOrder/offsite-exchange-order-cancelled-detail";
        }  else if (EnumOffsiteExchangeOrderStatus.BUYER_PAID.getCode().equals(detail.getStatus())) {
           return "offsiteExchangeOrder/offsite-exchange-order-buyerpaid-detail";
        }
        return "";
    }

    /**
     * 结束交易
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "结束线下汇换订单交易", monitor = true)
    @PostMapping(value = "/end")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:order:index")
    public BaseResult endOrder(@Valid @RequestBody OffisteExchangeOrderEndReq req) {
        return offsiteExchangeOrderService.endOrder(req);
    }

//    @SysLogAop(value = "解冻撮合交易的未解冻的买家下单保证金")
//    @PostMapping(value = "/thawOffsiteExchangeOrderMargin")
//    @ResponseBody
//    public BaseResult thawOffsiteExchangeOrderMargin(@Valid @RequestBody OffsiteExchangeOrderThawBuyerMargin req) {
//        return offsiteExchangeOrderService.thawOffsiteExchangeOrderMargin(req);
//    }
}



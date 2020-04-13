package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.OffsiteExchangeGoodsService;
import com.lxtx.im.admin.service.request.OffsiteExchangeCloseGoodsByAdminV5Req;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsDownloadReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsEditReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsPageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 下线换汇控制器
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 09:28
 * @Description
 */
@Controller
@RequestMapping("/offsite/exchange/goods")
public class OffsiteExchangeGoodsController {

    @Autowired
    private OffsiteExchangeGoodsService goodsService;

    /**
     * 跳转订单主页
     *
     * @return
     */
    @SysLogAop("跳转订单主页")
    @GetMapping(value = "/index")
    @RequiresPermissions("offsite:exchange:goods:index")
    public String toIndex() {
        return "offsiteExchangeGoods/offsite-exchange-goods-index";
    }

    /**
     * 商品列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("商品列表数据")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:goods:index")
    public BaseResult list(OffsiteExchangeGoodsPageReq req) {
        return goodsService.listPage(req);
    }

    /**
     * 商品列表下载
     *
     * @return
     */
    @SysLogAop(value = "商品列表下载", monitor = true)
    @RequestMapping(value = "/download")
    @RequiresPermissions("download:offsite:exchange:goods")
    public void orderDownload(HttpServletResponse response, OffsiteExchangeGoodsDownloadReq req) {
        goodsService.goodsDownload(response, req);
    }

    /**
     * 商品详情
     *
     * @return
     */
    @SysLogAop("换汇商品详情")
    @RequestMapping(value = "/detail")
    @RequiresPermissions("offsite:exchange:goods:index")
    public String detail(@RequestParam String detail, Model model) {
        JSONObject detailObject = JSON.parseObject(detail);
        if (!detailObject.containsKey("address")) {
            detailObject.put("address", "");
        }
        model.addAttribute("detail", detailObject);
        return "offsiteExchangeGoods/offsite-exchange-goods-detail";
    }

    @SysLogAop(value = "换汇商家商品", monitor = true)
    @PostMapping(value = "/up")
    @ResponseBody
    @RequiresPermissions("up:offsite:exchange:goods")
    public BaseResult up(@RequestBody OffsiteExchangeGoodsEditReq req) {
        return goodsService.up(req);
    }

    @SysLogAop(value = "关闭商家商品", monitor = true)
    @PostMapping(value = "/closeGoodsByAdmin")
    @ResponseBody
    @RequiresPermissions("close:offsite:exchange:goods")
    public BaseResult closeGoodsByAdmin(@RequestBody OffsiteExchangeCloseGoodsByAdminV5Req req) {
        return goodsService.closeGoodsByAdmin(req);
    }

    @SysLogAop(value = "换汇商品下架", monitor = true)
    @PostMapping(value = "/down")
    @ResponseBody
    @RequiresPermissions("down:offsite:exchange:goods")
    public BaseResult down(@RequestBody OffsiteExchangeGoodsEditReq req) {
        return goodsService.down(req);
    }

    @SysLogAop(value = "补旧数据的商品的付款方式")
    @PostMapping(value = "/syncOldGoodsOutPay")
    @ResponseBody
    public BaseResult syncOldGoodsOutPay() {
        return goodsService.syncOldGoodsOutPay();
    }
    
    @SysLogAop(value = "修复挂单无保证金，买家取消订单，补扣总账号的资金")
    @PostMapping(value = "/deductTheFundsOfTheTotalAccount")
    @ResponseBody
    public BaseResult deductTheFundsOfTheTotalAccount() {
    	return goodsService.deductTheFundsOfTheTotalAccount();
    }

}

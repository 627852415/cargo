package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.PayMerchantService;
import com.lxtx.im.admin.service.request.PayMerchantDetailReq;
import com.lxtx.im.admin.service.request.PayMerchantListPageReq;
import com.lxtx.im.admin.service.request.PayMerchantUpdateStatusReq;
import com.lxtx.im.admin.service.request.PayMerchantVerifyReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 商家管理
 * @author: CXM
 * @create: 2019-03-11 14:31
 */
@Controller
@RequestMapping("/merchant")
public class PayMerchantController {

    @Autowired
    private PayMerchantService payMerchantService;

    @SysLogAop("商家管理首页")
    @GetMapping("/index")
    @RequiresPermissions("merchant:index")
    public String index() {
        return "merchant/merchant-index";
    }

    /**
     * 商家列表
     *
     * @param req
     * @return
     */
    @SysLogAop("商家列表")
    @PostMapping(value = "/list")
    @RequiresPermissions("merchant:index")
    @ResponseBody
    public BaseResult listPage(PayMerchantListPageReq req) {
        return payMerchantService.listPage(req);
    }

    /**
     * 更新商家状态
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "更新商家状态", monitor = true)
    @PostMapping(value = "/update/status")
    @RequiresPermissions("merchant:index")
    @ResponseBody
    public BaseResult updateStatus(@Valid @RequestBody PayMerchantUpdateStatusReq req) {
        return payMerchantService.updateStatus(req);
    }

    /**
     * 商家详情
     *
     * @param req
     * @param model
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/03/12
     */
    @SysLogAop("商家详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("merchant:index")
    public String detail(PayMerchantDetailReq req, Model model) {
        return payMerchantService.detail(req, model);
    }

    @GetMapping(value = "/edit")
    public String edit(PayMerchantDetailReq req, Model model) {
        return payMerchantService.edit(req, model);
    }

    /**
     * 商家审核
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/03/12
     */
    @SysLogAop(value = "商家审核", monitor = true)
    @PostMapping(value = "/verify")
    @RequiresPermissions("merchant:index")
    @ResponseBody
    public BaseResult verify(PayMerchantVerifyReq req) {
        return payMerchantService.verify(req);
    }

}

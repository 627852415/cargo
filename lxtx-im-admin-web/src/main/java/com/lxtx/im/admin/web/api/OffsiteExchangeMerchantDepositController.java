package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.MerchantDepositService;
import com.lxtx.im.admin.service.request.MerchantDepositDelReq;
import com.lxtx.im.admin.service.request.MerchantDepositListPageReq;
import com.lxtx.im.admin.service.request.MerchantDepositSaveReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 线下换汇商家保证金信用管理
 *
 * @Author: liyunhua
 * @Date: 2019/4/19
 */
@Controller
@RequestMapping("/merchant/deposit")
public class OffsiteExchangeMerchantDepositController {

    @Autowired
    private MerchantDepositService merchantDepositService;

    /**
     * 商家保证金首页
     *
     * @param
     * @return java.lang.String
     * @author liyunhua
     * @date 2019/4/22
     */
    @SysLogAop("商家保证金首页")
    @GetMapping("/index")
    @RequiresPermissions("merchant:deposit:index")
    public String index() {
        return "offsiteExchange/merchant-deposit-index";
    }

    /**
     * 列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/4/19
     */
    @SysLogAop("商家保证金列表")
    @PostMapping(value = "/list/page")
    @ResponseBody
    @RequiresPermissions("merchant:deposit:index")
    public BaseResult listPage(MerchantDepositListPageReq req) {
        return merchantDepositService.listPage(req);
    }

    /**
     * 跳转到新增页面
     *
     * @param
     * @return java.lang.String
     * @author liyunhua
     * @date 2019/4/22
     */
    @SysLogAop("商家保证金信用分新增页")
    @GetMapping(value = "/add")
    @RequiresPermissions("merchant:deposit:add")
    public String add(Model model) {
        merchantDepositService.add(model);
        return "offsiteExchange/merchant-deposit-save";
    }

    /**
     * 跳转到编辑页面
     *
     * @param saveReq, model
     * @return java.lang.String
     * @author liyunhua
     * @date 2019/4/22
     */
    @SysLogAop("商家保证金信用分编辑页")
    @GetMapping(value = "/edit")
    @RequiresPermissions("merchant:deposit:edit")
    public String edit(MerchantDepositSaveReq saveReq, Model model) {
        merchantDepositService.detail(saveReq, model);
        return "offsiteExchange/merchant-deposit-save";
    }

    /**
     * 新增或保存
     *
     * @param req
     * @param session
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/4/22
     */
    @SysLogAop(value = "商家保证金信用分新增或保存", monitor = true)
    @PostMapping(value = "/save")
    @ResponseBody
    @RequiresPermissions("merchant:deposit:index")
    public BaseResult save(@Valid @RequestBody MerchantDepositSaveReq req, HttpSession session) {
        return merchantDepositService.save(req, session);
    }

    /**
     * 删除
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/4/22
     */
    @SysLogAop(value = "商家保证金信用分删除", monitor = true)
    @PostMapping(value = "/del")
    @ResponseBody
    @RequiresPermissions("merchant:deposit:del")
    public BaseResult del(@Valid @RequestBody MerchantDepositDelReq req) {
        return merchantDepositService.del(req);
    }

}

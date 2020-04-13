package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.BasePageReq;
import com.lxtx.im.admin.service.OffsiteExchangeRebateService;
import com.lxtx.im.admin.service.request.OffsiteExchangeRebateIdReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeRebateSaveReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 支付方式/返利管理
 *
 * @author CaiRH
 * @since 2019-05-28
 **/
@Controller
@RequestMapping("/offsite/exchange/rebate")
public class OffsiteExchangeRebateController {

    @Autowired
    private OffsiteExchangeRebateService offsiteExchangeRebateService;

    /**
     * 支付方式/返利配置首页
     */
    @SysLogAop("换汇支付方式/返利配置首页")
    @GetMapping("/index")
    @RequiresPermissions("offsite:exchange:rebate:index")
    public String index() {
        return "offsiteExchange/rebate-index";
    }

    /**
     * 跳转到新增页面
     */
    @SysLogAop("换汇支付方式/返利配置新增页")
    @GetMapping(value = "/add")
    @RequiresPermissions("add:offsite:exchange:rebate")
    public String add(Model model) {
        offsiteExchangeRebateService.add(model);
        return "offsiteExchange/rebate-save";
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop("换汇支付方式/返利编辑页")
    @GetMapping(value = "/edit")
    @RequiresPermissions("edit:offsite:exchange:rebate")
    public String edit(OffsiteExchangeRebateIdReq saveReq, Model model) {
        offsiteExchangeRebateService.detail(saveReq, model);
        return "offsiteExchange/rebate-save";
    }

    @SysLogAop(value = "换汇支付方式/返利保存操作", monitor = true)
    @PostMapping(value = "/save")
    @RequiresPermissions("offsite:exchange:rebate:index")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody OffsiteExchangeRebateSaveReq req, HttpSession session) {
        return offsiteExchangeRebateService.save(req, session);
    }

    @SysLogAop("换汇支付方式/返利列表数据")
    @PostMapping(value = "/list/page")
    @RequiresPermissions("offsite:exchange:rebate:index")
    @ResponseBody
    public BaseResult listPage(BasePageReq req) {
        return offsiteExchangeRebateService.listPage(req);
    }

    @SysLogAop(value = "换汇支付方式/返利删除操作", monitor = true)
    @PostMapping(value = "/del")
    @RequiresPermissions("del:offsite:exchange:rebate")
    @ResponseBody
    public BaseResult del(@Valid @RequestBody OffsiteExchangeRebateIdReq req) {
        return offsiteExchangeRebateService.del(req);
    }

    /**
     * 上传支付方式logo
     *
     * @param file
     * @return
     */
    @SysLogAop("上传支付方式logo")
    @PostMapping("/upload")
    @ResponseBody
    public BaseResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        return offsiteExchangeRebateService.upload(file);
    }
}

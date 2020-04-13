package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.LegalCoinService;
import com.lxtx.im.admin.service.request.LegalCoinDelReq;
import com.lxtx.im.admin.service.request.LegalCoinListPageReq;
import com.lxtx.im.admin.service.request.LegalCoinSaveReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 法币管理
 *
 * @Author: liyunhua
 * @Date: 2019/4/3
 */
@Controller
@RequestMapping("/legal/coin")
public class LegalCoinController {

    @Autowired
    private LegalCoinService legalCoinService;


    /**
     * 法币管理列表首页
     */
    @SysLogAop("法币管理列表首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("legal:coin:index")
    public String index() {
        return "walletMode/legal-coin-index";
    }

    /**
     * 法币管理列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/02/18
     */
    @SysLogAop("法币管理列表")
    @PostMapping(value = "/list/page")
    @ResponseBody
    @RequiresPermissions("legal:coin:index")
    public BaseResult listPage(LegalCoinListPageReq req) {
        return legalCoinService.listPage(req);
    }

    /**
     * 跳转到币种新增页面
     *
     * @param
     * @return java.lang.String
     * @author liyunhua
     * @date 2019/4/3
     */
    @SysLogAop(value = "跳转到币种新增页面", monitor = true)
    @GetMapping(value = "/add")
    @RequiresPermissions("legal:coin:add")
    public String add(Model model) {
        legalCoinService.add(model);
        return "walletMode/legal-coin-save";
    }

    /**
     * 跳转到编辑页面
     *
     * @param legalCoinSaveReq, model
     * @return java.lang.String
     * @author liyunhua
     * @date 2019/4/4
     */
    @SysLogAop("跳转到编辑页面")
    @GetMapping(value = "/edit")
    @RequiresPermissions("legal:coin:add")
    public String edit(LegalCoinSaveReq legalCoinSaveReq, Model model) {
        legalCoinService.detail(legalCoinSaveReq, model);
        return "walletMode/legal-coin-save";
    }

    /**
     * 法币新增或保存
     *
     * @param req
     * @param session
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/4/3
     */
    @SysLogAop(value = "法币新增或保存", monitor = true)
    @PostMapping(value = "/save")
    @ResponseBody
    @RequiresPermissions("legal:coin:index")
    public BaseResult save(@Valid @RequestBody LegalCoinSaveReq req, HttpSession session) {
        return legalCoinService.save(req, session);
    }

    /**
     * 法币删除
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/4/4
     */
    @SysLogAop(value = "法币删除", monitor = true)
    @PostMapping(value = "/del")
    @ResponseBody
    @RequiresPermissions("legal:coin:del")
    public BaseResult del(@Valid @RequestBody LegalCoinDelReq req) {
        return legalCoinService.del(req);
    }

}

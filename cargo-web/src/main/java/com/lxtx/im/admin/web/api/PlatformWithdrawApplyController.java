package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.PlatformWithdrawApplyService;
import com.lxtx.im.admin.service.request.*;
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
import javax.validation.Valid;
import java.util.Map;

/**
 * 系统提现
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Controller
@RequestMapping("/platform/withdraw/apply")
public class PlatformWithdrawApplyController {

    @Autowired
    private PlatformWithdrawApplyService platformService;

    @Autowired
    private AirdropService airdropService;

    /**
     * 系统提现首页
     */
    @SysLogAop("系统提现首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("platform:withdraw:apply:index")
    public String index(Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }

        return "platform/platform-withdraw-apply-index";
    }

    /**
     * 系统提现列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author
     * @date 2018/12/14
     */
    @SysLogAop("系统提现列表")
    @PostMapping(value = "/list/page")
    @RequiresPermissions("platform:withdraw:apply:index")
    @ResponseBody
    public BaseResult listPage(PlatformWithdrawApplyListReq req) {
        return platformService.listPage(req);
    }

    /**
     * 系统提现详情
     *
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("系统提现详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("platform:withdraw:apply:index")
    public String detail(PlatformWithdrawApplyDetailReq req, Model model) {
        return platformService.detail(req, model);
    }

    /**
     * 平台提款申请审核
     *
     * @param req
     * @return
     * @author tangdy
     */
    @SysLogAop(value = "平台提款申请审核", monitor = true)
    @PostMapping("/audit")
    @RequiresPermissions("platform:withdraw:apply:index")
    @ResponseBody
    public BaseResult platformWithdrawApplyAudit(@Valid PlatformWithdrawApplyAuditReq req) {
        return platformService.platformWithdrawApplyAudit(req);
    }

    /**
     * 系统提款申请列表下载
     *
     * @param  response
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-20 14:07
     */
    @SysLogAop(value = "系统提款申请列表下载", monitor = true)
    @RequestMapping(value = "/list/download")
    @RequiresPermissions("platform:withdraw:apply:index")
    public void download(HttpServletResponse response, PlatformWithdrawApplyListDownloadReq req) {
        platformService.download(response, req);
    }

}

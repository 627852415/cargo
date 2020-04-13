package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.RebateReportService;
import com.lxtx.im.admin.service.request.RebateDetailPageReq;
import com.lxtx.im.admin.service.request.RebateReportListPageReq;
import com.lxtx.im.admin.service.request.RebateReportListReq;
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

/**
 * 游戏返佣
 *
 * @Author: liyunhua
 * @Date: 2019/1/10
 */
@Controller
@RequestMapping("/rebate")
public class RebateReportController {

    @Autowired
    private RebateReportService rebateReportService;

    @SysLogAop("游戏返佣首页")
    @GetMapping("/index")
    @RequiresPermissions("rebate:index")
    public String index() {
        return "rebateReport/rebate-index.html";
    }

    /**
     * 获取返佣列表
     *
     * @param req
     * @return
     */
    @SysLogAop("获取返佣列表")
    @PostMapping("/list")
    @RequiresPermissions("rebate:index")
    @ResponseBody
    public BaseResult rebateList(RebateReportListPageReq req) {
        return rebateReportService.rebateList(req);
    }

    /**
     * 返佣列表下载
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "返佣列表下载", monitor = true)
    @GetMapping(value = "/list/download")
    @RequiresPermissions("rebate:index")
    public void rebateListDownload(HttpServletResponse response, RebateReportListReq req) {
         rebateReportService.rebateListDownload(response,req);
    }

    /**
     * 群主返佣详情页面
     * @param model
     * @return
     */
    @SysLogAop("群主返佣详情页面")
    @GetMapping("/group/owner/detail/index")
    @RequiresPermissions("rebate:index")
    public String groupOwnerRebateDetailIndex(RebateDetailPageReq req, Model model) {
        model.addAttribute("req", req);
        return "rebateReport/rebate-group-owner-detail.html";
    }

    /**
     * 下级返佣详情页面
     * @param model
     * @return
     */
    @SysLogAop("下级返佣详情页面")
    @GetMapping("/subordinate/detail/index")
    @RequiresPermissions("rebate:index")
    public String subordinateRebateDetailIndex(RebateDetailPageReq req, Model model) {
        model.addAttribute("req", req);
        return "rebateReport/rebate-subordinate-detail.html";
    }

    /**
     * 获取返佣详情
     *
     * @return
     */
    @SysLogAop("获取返佣详情")
    @PostMapping("/detail")
    @RequiresPermissions("rebate:index")
    @ResponseBody
    public BaseResult rebateDetail(RebateDetailPageReq req) {
        return rebateReportService.rebateDetail(req);
    }

    /**
     * 返佣详情下载
     *
     * @return
     */
    @SysLogAop(value = "返佣详情下载", monitor = true)
    @GetMapping(value = "/detail/download")
    @RequiresPermissions("rebate:index")
    public void rebateDetailDownload(HttpServletResponse response, RebateDetailPageReq req) {
         rebateReportService.rebateDetailDownload(response,req);
    }


}

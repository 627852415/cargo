package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.RebateReportService;
import com.lxtx.im.admin.service.request.RebateDetailPageReq;
import com.lxtx.im.admin.service.request.RebateReportListPageReq;
import com.lxtx.im.admin.service.request.RebatePlayerGameDetailPageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 游戏返佣
 *
 * @Author: liyunhua
 * @Date: 2019/1/10
 */
@Controller
@RequestMapping("/rebateReport")
public class ReportRebateDailyController {

    @Autowired
    private RebateReportService rebateReportService;

    @SysLogAop("游戏返佣首页")
    @GetMapping("/index")
    @RequiresPermissions("rebateReport:index")
    public String index() {
        return "rebateReport/rebate-report-index.html";
    }

    /**
     * 获取返佣列表
     *
     * @param req
     * @return
     */
    @SysLogAop("获取返佣列表数据")
    @PostMapping("/listPage")
    @RequiresPermissions("rebateReport:index")
    @ResponseBody
    public BaseResult listPage(RebateReportListPageReq req) {
        return rebateReportService.listPage(req);
    }

    /**
     * 返佣详情页面
     * @param model
     * @return
     */
    @SysLogAop("返佣详情页面")
    @GetMapping("/detail")
    @RequiresPermissions("rebateReport:index")
    public String detail(RebateDetailPageReq req, Model model) {
        model.addAttribute("req", req);
        return "rebateReport/rebate-report-detail.html";
    }

    /**
     * 获取返佣详情列表
     *
     * @return
     */
    @SysLogAop("获取返佣详情列表")
    @PostMapping("/userRebateDetail")
    @RequiresPermissions("rebateReport:index")
    @ResponseBody
    public BaseResult userRebateDetail(RebateDetailPageReq req) {
        return rebateReportService.userRebateDetailList(req);
    }

    /**
     * 对局详情页面
     * @param model
     * @return
     */
    @SysLogAop("对局详情页面")
    @GetMapping("/player")
    @RequiresPermissions("rebateReport:index")
    public String player(RebatePlayerGameDetailPageReq req, Model model) {
        model.addAttribute("req", req);
        return "rebateReport/rebate-report-detail-player.html";
    }

    /**
     * 获取玩家对局详情列表
     *
     * @return
     */
    @SysLogAop("获取玩家对局详情列表")
    @PostMapping("/playerGameDetail")
    @RequiresPermissions("rebateReport:index")
    @ResponseBody
    public BaseResult playerGameDetail(RebatePlayerGameDetailPageReq req) {
        return rebateReportService.playerGameDetail(req);
    }

}

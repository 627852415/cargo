package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.OtcOrderDayStatisticsService;
import com.lxtx.im.admin.service.request.OtcOrderDayStatisticsListPageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * OTC买卖比值报表
 *
 * @Author: liyunhua
 * @Date: 2019/3/7
 */
@Controller
@RequestMapping("/otc/order/day/statistics")
public class OtcOrderDayStatisticsController {

    @Autowired
    private OtcOrderDayStatisticsService otcOrderDayStatisticsService;

    /**
     * 报表首页
     */
    @SysLogAop("OTC买卖比值报表首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("otc:order:day:statistics:index")
    public String index() {
        return "otc/order-statistics-index";
    }

    /**
     * 报表列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("导出OTC买卖报表列表")
    @PostMapping(value = "/listPage")
    @RequiresPermissions("otc:order:day:statistics:index")
    @ResponseBody
    public BaseResult listPage(OtcOrderDayStatisticsListPageReq req) {
        return otcOrderDayStatisticsService.listPage(req);
    }

//    /**
//     * 手动生成OTC买卖报表
//     *
//     * @param
//     * @return com.lxtx.framework.common.base.BaseResult
//     * @author liyunhua
//     * @date 2018/12/18
//     */
//    @SysLogAop("手动生成OTC买卖报表")
//    @PostMapping(value = "/generateReport")
//    @ResponseBody
//    public BaseResult generateReport() {
//        return otcOrderDayStatisticsService.generateReport();
//    }

    /**
     * 导出excel文档
     *
     * @param response, req
     * @return void
     * @author Czh
     * @date 2018/12/20
     */
    @SysLogAop(value = "导出OTC买卖报表", monitor = true)
    @GetMapping(value = "/export/excel")
    @RequiresPermissions("otc:order:day:statistics:index")
    public void exportExcel(HttpServletResponse response, OtcOrderDayStatisticsListPageReq req) {
        otcOrderDayStatisticsService.exportExcel(response,req);
    }

}

package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.ChargeReportService;
import com.lxtx.im.admin.service.request.AirdropToSavePageReq;
import com.lxtx.im.admin.service.request.ChargeReportListPageReq;
import com.lxtx.im.admin.service.request.ChargeReportListReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 手续费报表
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Controller
@RequestMapping("/chargeReport")
public class ChargeReportController {


    @Autowired
    private ChargeReportService chargeReportService;

    @Autowired
    private AirdropService airdropService;


    /**
     * 手续费报表首页
     */
    @SysLogAop("手续费报表首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("chargeReport:index")
    public String coinChargeIndex(Model model, String coinId) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        if (StringUtils.isNotBlank(coinId)) {
            model.addAttribute("coinId", coinId);
        }

        return "chargeReport/charge-report-index";
    }

    /**
     * 手续费报表列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("手续费报表列表")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("chargeReport:index")
    public BaseResult listPage(ChargeReportListPageReq req) {
        return chargeReportService.listPage(req);
    }

//    /**
//     * 手动生成手续费报表
//     *
//     * @param
//     * @return com.lxtx.framework.common.base.BaseResult
//     * @author liyunhua
//     * @date 2018/12/18
//     */
//    @SysLogAop("手动生成手续费报表")
//    @PostMapping(value = "/generateReport")
//    @ResponseBody
//    public BaseResult generateReport() {
//        return chargeReportService.generateReport();
//    }


    /**
     * 导出excel文档
     *
     * @param response, req
     * @return void
     * @author liyunhua
     * @date 2018/12/20
     */
    @SysLogAop(value = "导出手续费报表", monitor = true)
    @GetMapping(value = "/exportExcel")
    @RequiresPermissions("chargeReport:index")
    public void exportExcel(HttpServletResponse response, ChargeReportListReq req) {
        chargeReportService.exportExcel(response, req);
    }

}

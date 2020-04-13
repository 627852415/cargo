package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.OffsiteExchangeUserStatisticsService;
import com.lxtx.im.admin.service.request.ExchangeUserStatisticsListPageReq;
import com.lxtx.im.admin.service.response.OffsiteExchangeUserStatisticsListPageResp;
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
 * <p>
 * 用户换汇统计
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 **/
@Controller
@RequestMapping("/exchange/user/statistics")
public class OffsiteExchangeUserStatisticsController {

    @Autowired
    private OffsiteExchangeUserStatisticsService offsiteExchangeUserStatisticsService;

    @SysLogAop("用户换汇统计首页")
    @GetMapping("/index")
    @RequiresPermissions("exchange:user:statistics:index")
    public String index(Model model) {
        offsiteExchangeUserStatisticsService.waveRateList(model);
        return "offsiteExchangeUserStatistics/exchange-user-statistics-index";
    }

    @SysLogAop("用户换汇统计列表分页数据")
    @PostMapping(value = "/list/page")
    @ResponseBody
    @RequiresPermissions("exchange:user:statistics:index")
    public BaseResult listPage(ExchangeUserStatisticsListPageReq req) {
        OffsiteExchangeUserStatisticsListPageResp resp = offsiteExchangeUserStatisticsService.listPage(req);
        return BaseResult.success(resp);
    }

    @SysLogAop(value = "用户换汇统计列表下载", monitor = true)
    @GetMapping(value = "/export/excel")
    @RequiresPermissions("exchange:user:statistics:index")
    public void exportExcel(HttpServletResponse response, ExchangeUserStatisticsListPageReq req) {
        offsiteExchangeUserStatisticsService.exportExcel(response, req);
    }

}

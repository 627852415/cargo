package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.AssetStatisticsDailyListService;
import com.lxtx.im.admin.service.request.AirdropToSavePageReq;
import com.lxtx.im.admin.service.request.AssetStatisticsDalyListDetailReq;
import com.lxtx.im.admin.service.request.AssetStatisticsListExportReq;
import com.lxtx.im.admin.service.request.AssetStatisticsListReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
  *   资产统计列表
 　　* @author Lin hj
 　　* @redoDateTimes 2019/6/14 10:15
 */
@Controller
@RequestMapping("/asset/coin/statistics")
public class AssetStatisticsListController {

    @Autowired
    private AssetStatisticsDailyListService assetStatisticsListService;
    @Autowired
    private AirdropService airdropService;

    /**
     * 首页
     */
    @SysLogAop("跳转资产统计首页")
    @GetMapping(value = "/list")
    @RequiresPermissions("statistics:coin:index")
    public String index(Model model)
    {
        return "asset/asset-statistics-index";
    }

    /**
     * 列表
     */
    @SysLogAop("查询资产统计列表")
    @RequestMapping(value = "/list/page", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @RequiresPermissions("statistics:coin:index")
    public BaseResult listPage(AssetStatisticsListReq req) {
        return assetStatisticsListService.listPage(req);
    }

    @SysLogAop(value = "资产统计导出", monitor = true)
    @GetMapping(value = "/list/down")
    @RequiresPermissions("statistics:coin:index")
    public void downData(HttpServletResponse response,String id) {
        assetStatisticsListService.downData(response,id);
    }


    /**
     * 详情页
     *
     * @param model
     * @return
     */
    @SysLogAop("跳转资产统计详情页")
    @GetMapping(value = "/detail")
    @RequiresPermissions("statistics:coin:index")
    public String detail(Long recordsDate, Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        model.addAttribute("recordsDate", recordsDate);
        return "asset/statistics-statistics-detail";
    }


    /**
     * 详情列表数据
     *
     * @return
     */
    @SysLogAop("查询资产统计详情列表数据")
    @PostMapping(value = "/detail/data")
    @ResponseBody
    @RequiresPermissions("statistics:coin:index")
    public BaseResult detail(AssetStatisticsDalyListDetailReq assetStatisticsDalyListDetailReq) {
        return assetStatisticsListService.detail(assetStatisticsDalyListDetailReq);
    }

    @SysLogAop(value = "导出资产快照报表", monitor = true)
    @GetMapping(value = "/detail/excel")
    @RequiresPermissions("statistics:coin:index")
    public void createDetailExcel(HttpServletResponse response,  AssetStatisticsListExportReq assetStatisticsListExportReq ) {
        assetStatisticsListService.createDetailExcel(response,assetStatisticsListExportReq);
    }


}

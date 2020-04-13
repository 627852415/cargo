package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.UserCoinDailyListService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * 快照管理
 *
 * @author CaiRH
 * @since 2019-05-31
 */
@Controller
@RequestMapping("/user/coin/snapshot")
public class UserCoinDailyListController {

    @Autowired
    private UserCoinDailyListService userCoinDailyListService;
    @Autowired
    private AirdropService airdropService;

    /**
     * 首页
     */
    @SysLogAop("资金快照首页")
    @GetMapping(value = "/list")
    @RequiresPermissions("user:coin:snapshot:list")
    public String index(Model model) {
        return "userCoin/snapshot-index";
    }

    /**
     * 列表
     */
    @SysLogAop("资金快照列表数据")
    @PostMapping(value = "/list/page")
    @RequiresPermissions("user:coin:snapshot:list")
    @ResponseBody
    public BaseResult listPage(UserCoinDailyListReq req) {
        return userCoinDailyListService.listPage(req);
    }

    @SysLogAop(value = "资金快照数据下载", monitor = true)
    @GetMapping(value = "/list/down")
    @RequiresPermissions("user:coin:snapshot:list")
    public void downData(HttpServletResponse response,String id) {
        userCoinDailyListService.downData(response,id);
    }

    /**
     * 详情页
     *
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("资金快照详情页")
    @GetMapping(value = "/detail")
    @RequiresPermissions("user:coin:snapshot:list")
    public String detail(UserCoinDailyDetailIndexReq req, Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        model.addAttribute("recordsDate", req.getRecordsDate());
        return "userCoin/snapshot-detail";
    }

    /**
     * 详情列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("资金快照详情列表数据")
    @PostMapping(value = "/detail/data")
    @RequiresPermissions("user:coin:snapshot:list")
    @ResponseBody
    public BaseResult detailData(UserCoinDailyDetailReq req) {
        return userCoinDailyListService.detail(req);
    }

    /**
     * 导出快照报表
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "导出快照报表", monitor = true)
    @GetMapping(value = "/detail/excel")
    @RequiresPermissions("user:coin:snapshot:list")
    public void createDetailExcel(HttpServletResponse response,UserCoinDailyDetailReq req) {
        userCoinDailyListService.createDetailExcel(response,req);
    }

    /**
     * 重新生成资金快照
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "重新生成资金快照", monitor = true)
    @PostMapping(value = "/rebuild")
    @RequiresPermissions("user:coin:snapshot:list")
    @ResponseBody
    public BaseResult rebuildSnapshot(@Valid @RequestBody UserCoinDailySnapshotRebuildReq req) {
        return userCoinDailyListService.rebuildSnapshot(req);
    }

}

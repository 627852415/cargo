package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.UserCoinAssetService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 资金流水
 *
 * @Author: tangdy
 * @Date: 2018/12/14
 */
@Controller
@RequestMapping("/user/coin/asset")
public class UserCoinAssetController {

    @Autowired
    private UserCoinAssetService assetService;

    @Autowired
    private AirdropService airdropService;

    /**
     * 首页
     */
    @SysLogAop("资金流水首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("user:coin:asset:index")
    public String index(Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }

        return "asset/user-coin-asset-index";
    }

    /**
     * 列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author
     * @date 2018/12/14
     */
    @SysLogAop("资金流水列表数据")
    @PostMapping(value = "/list/page")
    @RequiresPermissions("user:coin:asset:index")
    @ResponseBody
    public BaseResult listPage(UserCoinAssetListReq req) {
        return assetService.listPage(req);
    }


    /**
     * 详情
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("资金流水详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("user:coin:asset:index")
    public String detail(UserCoinAssetDetailReq req, Model model) {
        return assetService.detail(req, model);
    }

    /**
     * 列表导出
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author
     * @date 2018/12/14
     */
    @SysLogAop(value = "资金流水报表导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("user:coin:asset:index")
    public void downloadList(HttpServletResponse response, UserCoinAssetListReq req) {
        assetService.downloadList(response, req);
    }

    /**
     * 资金流水异常报表生成跳转页
     *
     * @return
     */
    @SysLogAop("资金流水异常报表生成跳转页")
    @GetMapping(value = "/toDiffReport")
    @RequiresPermissions("user:coin:asset:index")
    public String add() {
        return "devManage/coin-flow-diff-report-save";
    }

    /**
     * 手动生成手续费报表
     *
     * @param
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/18
     */
    @SysLogAop(value = "手动生成资金流水差异报表", monitor = true)
    @PostMapping(value = "/generateFlowReport")
    @RequiresPermissions("user:coin:asset:index")
    @ResponseBody
    public BaseResult generateDiffFlowReport(@RequestBody UserCoinAssetDiffReq req) {
        assetService.generateDiffFlowReport(req);
        return BaseResult.success();
    }


}

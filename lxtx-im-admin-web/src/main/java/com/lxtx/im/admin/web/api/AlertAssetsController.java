package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AlertAssetsService;
import com.lxtx.im.admin.service.enums.EnumUserCoinAssetSubType;
import com.lxtx.im.admin.service.request.AlertAssetsDetailReq;
import com.lxtx.im.admin.service.request.AlertAssetsReq;
import com.lxtx.im.admin.service.request.AlertAssetsSaveReq;
import com.lxtx.im.admin.service.response.AlertAssetsDetailResp;
import com.lxtx.im.admin.service.response.AlertAssetsTypeResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

/**
 * @Description: 资产报警后台管理
 * @author: ZhangZhongWu
 * @date: 2019/6/19 11:16
 */
@Controller
@RequestMapping("/alertAssets")
public class AlertAssetsController {

    @Resource
    private AlertAssetsService alertAssetsService;

    /**
     * 跳转到页面
     *
     * @param model
     * @return
     */
    @SysLogAop("跳转资产报警首页")
    @GetMapping("/index")
    @RequiresPermissions("alertAssets:index")
    public String index(Model model) {
        Map<String, String> subTypeMap = new HashMap<>();
        getSubType().forEach(o ->
                subTypeMap.put(o.getCode() + "", o.getDescription())
        );
        model.addAttribute("subTypeMap", JSONObject.toJSONString(subTypeMap));
        return "alertAssets/index";
    }

    /**
     * 报警列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("查询资产报警列表数据")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("alertAssets:index")
    public BaseResult list(AlertAssetsReq req) {
        return alertAssetsService.listPage(req);
    }

    /**
     * 新增页面
     *
     * @param model
     * @return
     */
    @SysLogAop(value = "跳转资产报警新增页面", monitor = true)
    @GetMapping(value = "/add")
    @RequiresPermissions("alertAssets:add")
    public String add(Model model) {
        model.addAttribute("subTypeMap", JSONObject.toJSONString(getSubType()));
        return "alertAssets/add";
    }

    private List<AlertAssetsTypeResp> getSubType() {
        List<AlertAssetsTypeResp> subTypeList = new ArrayList<>();
        Arrays.stream(EnumUserCoinAssetSubType.values()).forEach(o -> {
            AlertAssetsTypeResp typeResp = new AlertAssetsTypeResp();
            typeResp.setCode(o.getCode());
            typeResp.setDescription(o.getDescription());
            subTypeList.add(typeResp);
        });
        return subTypeList;
    }

    /**
     * 编辑页面
     *
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("跳转资产报警编辑页面")
    @GetMapping(value = "/edit")
    @RequiresPermissions("alertAssets:add")
    public String edit(AlertAssetsDetailReq req, Model model) {
        BaseResult result = alertAssetsService.getById(req);
        if (result.isSuccess()) {
            AlertAssetsDetailResp data = JSONObject.parseObject(JSON.toJSONString(result.getData())
                    , AlertAssetsDetailResp.class);
            model.addAttribute("alert", data);
        }
        model.addAttribute("subTypeMap", JSONObject.toJSONString(getSubType()));
        return "alertAssets/edit";
    }

    /**
     * 资产报警新增或编辑
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "资产报警新增或编辑", monitor = true)
    @PostMapping(value = "/save")
    @ResponseBody
    @RequiresPermissions("alertAssets:index")
    public BaseResult save(@Valid @RequestBody AlertAssetsSaveReq req) {
        return alertAssetsService.save(req);
    }

    /**
     * 资产报警新增或编辑
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "资产报警启用或禁用", monitor = true)
    @PostMapping(value = "/update")
    @ResponseBody
    @RequiresPermissions("alertAssets:update")
    public BaseResult updateState(@Valid @RequestBody AlertAssetsSaveReq req) {
        return alertAssetsService.update(req);
    }
}

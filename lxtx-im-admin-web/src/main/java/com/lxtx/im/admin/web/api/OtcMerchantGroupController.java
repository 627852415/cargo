package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.OtcMerchantGroupService;
import com.lxtx.im.admin.service.request.DictInfoReq;
import com.lxtx.im.admin.service.request.OtcMerchantGroupDictSaveReq;
import com.lxtx.im.admin.service.request.OtcMerchantGroupListPageReq;
import com.lxtx.im.admin.service.response.DictInfoResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * OTC商家分组
 *
 * @Author: liyunhua
 * @Date: 2019/4/2
 */
@Controller
@RequestMapping("/otc/merchant/group")
public class OtcMerchantGroupController {

    @Autowired
    private OtcMerchantGroupService otcMerchantGroupService;

    @Autowired
    private DictService dictService;

    /**
     * OTC商家分组首页
     */
    @SysLogAop("OTC商家分组首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("otc:merchant:group:index")
    public String index() {
        return "otc/merchant-group-index";
    }

    /**
     * OTC商家分组列表
     *
     * @param req
     * @return
     */
    @SysLogAop("OTC商家分组列表数据")
    @PostMapping(value = "/list/page")
    @RequiresPermissions("otc:merchant:group:index")
    @ResponseBody
    public BaseResult listPage(OtcMerchantGroupListPageReq req) {
        return otcMerchantGroupService.listPage(req);
    }

    /**
     * OTC商家分组编辑页面跳转
     *
     * @return
     */
    @SysLogAop("OTC商家分组编辑页")
    @GetMapping(value = "/edit")
    @RequiresPermissions("otc:merchant:group:edit")
    public String edit(DictInfoReq req, Model model) {
        BaseResult result = dictService.info(req);
        DictInfoResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), DictInfoResp.class);
        model.addAttribute("dict", resp);
        return "otc/merchant-group-edit";
    }

    /**
     * OTC商家分组字典新增
     *
     * @return
     */
    @SysLogAop("OTC商家分组新增页")
    @GetMapping(value = "/add")
    @RequiresPermissions("otc:merchant:group:add")
    public String add() {
        return "otc/merchant-group-add";
    }

    /**
     * OTC商家分组字典保存或更新
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "OTC商家分组字典保存或更新", monitor = true)
    @PostMapping(value = "/save")
    @RequiresPermissions("otc:merchant:group:save")
    @ResponseBody
    public BaseResult saveOrUpdate(@Valid @RequestBody OtcMerchantGroupDictSaveReq req) {
        return otcMerchantGroupService.saveOrUpdate(req);
    }

}

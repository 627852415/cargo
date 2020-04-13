package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.AirdropDetailPageResp;
import com.lxtx.im.admin.service.response.AirdropResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @description: 空投管理
 * @author: CXM
 * @create: 2018-08-31 09:35
 **/
@Controller
@RequestMapping("/airdrop")
public class AirdropController {

    @Autowired
    private AirdropService airdropService;

    /**
     * 空投列表
     *
     * @return
     */
    @SysLogAop("跳转空投首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("airdrop:index")
    public String toIndex(Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        return "wallet/airdrop-index";
    }

    /**
     * 保存空投
     *
     * @return
     */
    @SysLogAop(value = "跳转空投编辑页", monitor = true)
    @GetMapping(value = "/save")
    @RequiresPermissions("airdrop:save")
    public String toSavePage(Model model, AirdropToSavePageReq req) {
        BaseResult result = airdropService.getAirdropData(req);
       if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>)result.getData();
            model.addAttribute("coins", map.get("coins"));
            if (StringUtils.isNotBlank(req.getId())) {
                AirdropResp airdropResp = JSONObject.parseObject(JSON.toJSONString(result.getData()), AirdropResp.class);
                model.addAttribute("airdrop", airdropResp);
            }
        }
        return "wallet/airdrop-save";
    }

    /**
     * 空投列表数据
     *
     * @param airdropReq
     * @param session
     * @return
     */
    @SysLogAop("查询空投列表数据")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("airdrop:index")
    public BaseResult list(AirdropReq airdropReq, HttpSession session) {
        BaseResult result = airdropService.listPage(airdropReq, session);
        return result;
    }

    /**
     * 删除空投
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "删除空投", monitor = true)
    @PostMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("airdrop:delete")
    public BaseResult delete(@RequestBody DeleteAirdropReq req) {
        return airdropService.deleteById(req);
    }

    /**
     * 保存或编辑空投
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "保存或编辑空投",monitor = true)
    @PostMapping(value = "/add")
    @ResponseBody
    @RequiresPermissions("airdrop:index")
    public BaseResult saveOrEdit(@RequestBody SaveAirdropReq req) {
        return airdropService.saveOrEdit(req);
    }


    /**
     * 空投下具体详细列表分页
     *
     * @param req
     * @return
     */
    @SysLogAop("查询空投详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("airdrop:index")
    public String detailAirDrop(Model model, AirdropDetailReq req) {
        BaseResult result = airdropService.detailAirdropList(req);
        if (result.isSuccess()) {
            AirdropDetailPageResp data = JSONObject.parseObject(JSON.toJSONString(result.getData()), AirdropDetailPageResp.class);
            model.addAttribute("list", data);
            model.addAttribute("airdrop",data.getAirdropResp());
            model.addAttribute("id", req.getId());
        }
        return "wallet/airdrop-detail";
    }

}

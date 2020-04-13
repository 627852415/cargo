package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.BasePageReq;
import com.lxtx.im.admin.service.OffsiteExchangeWaveRateService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.OffsiteExchangeWaveAreaRateListResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 换汇汇率配置管理
 *
 * @author CaiRH
 * @since 2019-05-24 13:41
 **/
@Controller
@RequestMapping("/offsite/exchange/wave/rate")
public class OffsiteExchangeWaveRateController {

    @Autowired
    private OffsiteExchangeWaveRateService offsiteExchangeWaveRateService;

    /**
     * 汇率配置首页
     */
    @SysLogAop("换汇汇率配置首页")
    @GetMapping("/index")
    @RequiresPermissions("offsite:exchange:wave:rate:index")
    public String index() {
        return "offsiteExchange/wave-rate-index";
    }

    /**
     * 跳转到新增页面
     */
    @SysLogAop("换汇汇率新增页面")
    @GetMapping(value = "/add")
    @RequiresPermissions("add:offsite:exchange:wave:rate")
    public String add(Model model) {
        offsiteExchangeWaveRateService.add(model);
        return "offsiteExchange/wave-rate-save";
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop("换汇汇率编辑页面")
    @GetMapping(value = "/edit")
    @RequiresPermissions("edit:offsite:exchange:wave:rate")
    public String edit(OffsiteExchangeWaveRateIdReq saveReq, Model model) {
        offsiteExchangeWaveRateService.detail(saveReq, model);
        return "offsiteExchange/wave-rate-save";
    }

    @SysLogAop(value = "换汇汇率保存操作", monitor = true)
    @PostMapping(value = "/save")
    @RequiresPermissions("offsite:exchange:wave:rate:index")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody OffsiteExchangeWaveRateSaveReq req) {
        return offsiteExchangeWaveRateService.save(req);
    }

    @SysLogAop("换汇汇率列表数据")
    @PostMapping(value = "/list/page")
    @RequiresPermissions("offsite:exchange:wave:rate:index")
    @ResponseBody
    public BaseResult listPage(BasePageReq req) {
        return offsiteExchangeWaveRateService.listPage(req);
    }

    @SysLogAop(value = "换汇汇率删除操作", monitor = true)
    @PostMapping(value = "/del")
    @RequiresPermissions("del:offsite:exchange:wave:rate")
    @ResponseBody
    public BaseResult del(@Valid @RequestBody OffsiteExchangeWaveRateIdReq req) {
        return offsiteExchangeWaveRateService.del(req);
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop("换汇汇率编辑页面")
    @GetMapping(value = "/areaEdit")
    @RequiresPermissions("offsite:exchange:wave:rate:area:edit")
    public String areaRateEdit(@Valid OffsiteExchangeWaveAreaRateListReq saveReq, Model model) {
        List<OffsiteExchangeWaveAreaRateListResp> list = offsiteExchangeWaveRateService.areaRateList(saveReq);
        model.addAttribute("list", list);
        return "offsiteExchange/wave-rate-area-edit";
    }

    /**
     * 更新换汇地区波动汇率
     */
    @SysLogAop(value = "更新换汇地区波动汇率", monitor = true)
    @PostMapping(value = "/areaUpdate")
    @RequiresPermissions("offsite:exchange:wave:rate:index")
    @ResponseBody
    public BaseResult areaUpdate(@Valid @RequestBody OffsiteExchangeWaveRateAreaUpdateReq req) {
        return offsiteExchangeWaveRateService.areaRateUpdate(req);
    }

    @SysLogAop("换汇获取当前汇率")
    @PostMapping(value = "/getCurrentRate")
    @RequiresPermissions("offsite:exchange:wave:rate:index")
    @ResponseBody
    public BaseResult getCurrentRate(@Valid @RequestBody CurrencyExchangeRateReq req) {
        return offsiteExchangeWaveRateService.getCurrentRate(req);
    }

}

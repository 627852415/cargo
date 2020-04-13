package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.OtcService;
import com.lxtx.im.admin.service.request.OtcOrderReq;
import com.lxtx.im.admin.service.request.UpdateOtcOrderCheckStateReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 交易流水
 *
 * @author CaiRH
 * @since 2018-09-27
 */
@Controller
@RequestMapping("/otc/order")
public class OtcOrderController {

    @Autowired
    private OtcService otcService;

    /**
     * 交易流水列表
     *
     * @return
     */
    @SysLogAop("交易流水列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("otc:order:index")
    public String toIndex() {
        return "admin/otcOrder-index";
    }

    /**
     * 交易流水列表数据
     *
     * @param req
     * @param session
     * @return
     */
    @SysLogAop("交易流水列表数据")
    @PostMapping(value = "/list")
    @RequiresPermissions("otc:order:index")
    @ResponseBody
    public BaseResult list(@Valid @RequestBody OtcOrderReq req, HttpSession session) {
        BaseResult result = otcService.listPage(req, session);
        Map<String,Object> map = new HashMap<>(1);
        map.put("dataResult",result);
        return BaseResult.success(map);
    }

    /**
     * 审核订单
     *
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "审核OTC订单", monitor = true)
    @PostMapping(value = "/update/check/state")
    @RequiresPermissions("otc:order:update:state")
    @ResponseBody
    public BaseResult updateCheckState(@Valid @RequestBody UpdateOtcOrderCheckStateReq req, HttpSession session) {
        BaseResult result = otcService.updateCheckState(req, session);
        Map<String,Object> map = new HashMap<>(1);
        map.put("dataResult",result);
        return BaseResult.success(map);
    }


}



package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.BankcardService;
import com.lxtx.im.admin.service.request.BankcardListPageReq;
import com.lxtx.im.admin.service.request.OtcBindBankcardNewReq;
import com.lxtx.im.admin.service.request.OtcBindBankcardUpdateReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * OTC银行卡管理
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Controller
@RequestMapping("/otc/bankcard")
public class OtcBankcardController {

    @Autowired
    private BankcardService bankcardService;

    /**
     * 银行卡列表首页
     */
    @SysLogAop("银行卡列表首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("otc:bankcard:index")
    public String otcBankcardIndex(Model model) {
    	bankcardService.index(model);
        return "otc/bankcard-index";
    }

    /**
     * 银行卡列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/02/18
     */
    @SysLogAop("银行卡列表")
    @PostMapping(value = "/listPage")
    @RequiresPermissions("otc:bankcard:index")
    @ResponseBody
    public BaseResult listPage(BankcardListPageReq req) {
        return bankcardService.listPage(req);
    }

    /**
     * 解绑银行卡
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/2/19
     */
    @SysLogAop(value = "解绑银行卡", monitor = true)
    @PostMapping(value = "/unbind")
    @RequiresPermissions("otc:bankcard:unbind")
    @ResponseBody
    public BaseResult unbind(@RequestBody OtcBindBankcardUpdateReq req) {
        return bankcardService.unbind(req);
    }

    /**
     * 绑定银行卡
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/2/22
     */
    @SysLogAop(value = "绑定银行卡", monitor = true)
    @PostMapping(value = "/bind")
    @RequiresPermissions("otc:bankcard:bind")
    @ResponseBody
    public BaseResult bind(OtcBindBankcardNewReq req) {
        return bankcardService.bind(req);
    }


}

package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CapitalService;
import com.lxtx.im.admin.service.request.CapitalDetailReq;
import com.lxtx.im.admin.service.request.CapitalExcelOutputReq;
import com.lxtx.im.admin.service.request.CapitalReq;
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
import javax.servlet.http.HttpSession;

/**
 * 交易流水
 *
 * @author CaiRH
 * @since 2018-09-27
 */
@Controller
@RequestMapping("/capital")
public class CapitalController {

    @Autowired
    private CapitalService capitalService;

    /**
     * 交易流水列表
     *
     * @return
     */
    @SysLogAop("跳转交易流水列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("capital:index")
    public String toIndex() {
        return "wallet/capital-index";
    }


    /**
     * 交易流水列表数据
     *
     * @param capitalReq
     * @param session
     * @return
     */
    @SysLogAop("查询交易流水列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("capital:index")
    public BaseResult listPage(CapitalReq capitalReq, HttpSession session) {
        return capitalService.listPage(capitalReq, session);
    }


    /**
     * 列表导出
     */
    @SysLogAop(value = "交易记录列表导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("capital:index")
    public void downloadList(HttpServletResponse response, CapitalExcelOutputReq capitalReq) {
        capitalService.downloadList(response, capitalReq);
    }

    /**
     * 查看详情
     *
     * @param capitalDetailReq
     * @param model
     * @return
     */
    @SysLogAop("查询交易流水详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("capital:index")
    public String detail(CapitalDetailReq capitalDetailReq, Model model) {
        return capitalService.detail(capitalDetailReq, model);
    }
}



package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AdviceFeedbackService;
import com.lxtx.im.admin.service.request.AdviceFeedbackListPageReq;
import com.lxtx.im.admin.service.response.AdviceFeedbackDetailResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 意见反馈后台管理
 * @author: ZhangZhongWu
 * @date: 2019/7/4 16:44
 */
@Controller
@RequestMapping("/adviceFeedback")
public class AdviceFeedbackController {

    @Autowired
    private AdviceFeedbackService adviceFeedbackService;

    /**
     * 跳转主页
     *
     * @return
     */
    @SysLogAop("跳转意见反馈列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("advice:feedback:list")
    public String toIndex() {
        return "adviceFeedback/index";
    }

    /**
     * 反馈列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("查询意见反馈数据列表")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("advice:feedback:list")
    public BaseResult list(AdviceFeedbackListPageReq req) {
        return adviceFeedbackService.listPage(req);
    }

    /**
     * 反馈详情
     *
     * @return
     */
    @SysLogAop("查询意见反馈详情")
    @RequestMapping(value = "/detail")
    @RequiresPermissions("advice:feedback:list")
    public String detail(@RequestParam(name = "id") String id, Model model) {
        BaseResult baseResult = adviceFeedbackService.detail(id);
        if (baseResult.isSuccess() && null != baseResult.getData()) {
            AdviceFeedbackDetailResp data = JSONObject.parseObject(JSON.toJSONString(baseResult.getData())
                    , AdviceFeedbackDetailResp.class);
            if (StringUtils.isNotBlank(data.getPhoneCode()) && StringUtils.isNotBlank(data.getTelephone())) {
                data.setFullTelephone(data.getPhoneCode().concat(" ").concat(data.getTelephone()));
            }
            model.addAttribute("detail", data);
        }
        return "adviceFeedback/detail";
    }

    /**
     * 导出报表
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "导出反馈数据", monitor = true)
    @RequestMapping(value = "/download")
    @RequiresPermissions("advice:feedback:list")
    public void dataDownload(HttpServletResponse response, AdviceFeedbackListPageReq req) {
        adviceFeedbackService.createExcel(response, req);
    }
}
package com.lxtx.im.admin.web.api;

import javax.validation.Valid;

import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.PatternWhiteListService;
import com.lxtx.im.admin.service.request.PatternWhiteListModifyReq;
import com.lxtx.im.admin.service.request.PatternWhiteListPageReq;

@Controller
@RequestMapping("/pattern/whiteList")
public class PatternWhiteListController {

	@Autowired
	private PatternWhiteListService patternWhiteListService;

    @SysLogAop("白名单首页")
	@GetMapping("/index")
    @RequiresPermissions("patternWhiteList:index")
    public String index(Model model,PatternWhiteListPageReq req) {
		model.addAttribute("countryCode",req.getCountryCode());
        return "patternWhiteList/patternWhiteList-index.html";
    }
	
	/**
     * 分页获取用户列表
     *
     * @param req
     * @return
     */
    @SysLogAop("白名单用户列表数据")
    @PostMapping("/listPage")
    @RequiresPermissions("patternWhiteList:index")
    @ResponseBody
    public BaseResult listPage(@Valid PatternWhiteListPageReq req) {
        return patternWhiteListService.listPage(req);
    }

    @SysLogAop(value = "修改白名单列表数据", monitor = true)
    @PostMapping("/modifyWhiteList")
    @RequiresPermissions("pattern:whiteList:modifyWhiteList")
	@ResponseBody
    public BaseResult modifyWhiteList(@Valid PatternWhiteListModifyReq req) {
		return patternWhiteListService.modifyWhiteList(req);
	}
}

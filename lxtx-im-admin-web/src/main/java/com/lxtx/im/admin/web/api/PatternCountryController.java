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
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.PatternCountryService;
import com.lxtx.im.admin.service.request.PatternCountryListPageReq;
import com.lxtx.im.admin.service.request.PatternCountryModifyReq;

@Controller
@RequestMapping("/pattern/country")
public class PatternCountryController {

	@Autowired
    private PatternCountryService patternCountryService;
	
	@Autowired
	private DictService dictService;

	@SysLogAop("国家及地区管理首页")
	@GetMapping("/index")
	@RequiresPermissions("patternCountry:index")
    public String index(Model model) {
		String openFlag = dictService.getDictValue(DictConstants.DICT_DOMAIN_GLOBAL,DictConstants.DICT_KEY_WALLET_PATTERN_WHITE_LIST_OPEN);
		model.addAttribute("domain",DictConstants.DICT_DOMAIN_GLOBAL);
		model.addAttribute("iKey",DictConstants.DICT_KEY_WALLET_PATTERN_WHITE_LIST_OPEN);
		model.addAttribute("openFlag",openFlag);
		return "patternCountry/patternCountry-index.html";
    }

	@SysLogAop("国家及地区管理列表数据")
	@PostMapping("/listPage")
	@RequiresPermissions("patternCountry:index")
    @ResponseBody
    public BaseResult listPage(PatternCountryListPageReq req) {
        return patternCountryService.listPage(req);
    }

	@SysLogAop(value = "国家及地区管理修改钱包模式", monitor = true)
	@PostMapping("/modifyPattern")
	@RequiresPermissions("pattern:country:modifyPattern")
	@ResponseBody
    public BaseResult modifyPatternCountry(@Valid PatternCountryModifyReq req) {
		return patternCountryService.modifyPatternCountry(req);
	}
}

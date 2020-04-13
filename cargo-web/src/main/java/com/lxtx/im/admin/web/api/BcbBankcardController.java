package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.BankcardTypeService;
import com.lxtx.im.admin.service.BcbBankcardService;
import com.lxtx.im.admin.service.SysUserService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.BcbBankApplyAuditDetailResp;
import com.lxtx.im.admin.service.response.BcbBankCardNumDetailResp;
import com.lxtx.im.admin.service.response.BcbBankCardTypeDetailResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * BCB银行卡
 *
 * @author : CaiRH
 * @since : 2019-04-23
 */
@Controller
@RequestMapping("/bcb/bankcard")
public class BcbBankcardController {

    @Autowired
    private BcbBankcardService bcbBankcardService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private BankcardTypeService bankcardTypeService;
    @Autowired
    private AirdropService airdropService;

    @SysLogAop("跳转BCB银行卡管理首页")
    @GetMapping("/index")
    @RequiresPermissions("/bcb/bankcard/index")
    public String index() {
        return "bcb:bankcard:applylist";
    }

    @SysLogAop("跳转BCB银行卡号首页")
    @GetMapping("/index/cardnum")
    @RequiresPermissions("bcb:bankcard:cardlist")
    public String toCardIndex() {
        return "bcbBank/bcbBankcard-cardnum-index";
    }

    /**
     * 银行卡列表
     *
     * @param req
     * @return
     */
    @SysLogAop("查询BCB银行卡号列表")
    @PostMapping(value = "/cardnum/list")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult cardNumlistPage(BcbBankCardNumberPageReq req) {
        return bcbBankcardService.cardList(req);
    }

    @SysLogAop(value = "BCB银行卡号新增", monitor = true)
    @PostMapping(value = "/cardnum/add")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult cardNumAdd(@RequestBody BcbBankCardNumReq req) {
        return bcbBankcardService.cardAdd(req);
    }

    @SysLogAop("BCB银行卡新增页")
    @GetMapping(value = "/toSave")
    @RequiresPermissions("/bcb/bankcard/index")
    public String toSave(Model model) {
        BaseResult result = bankcardTypeService.selectAllList();
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("cardType", map.get("list"));
        }
        return "bcbBank/bcbBankcard-cardnum-save";
    }

    @SysLogAop("跳转BCB银行卡编辑页")
    @GetMapping(value = "/toEdit")
    @RequiresPermissions("/bcb/bankcard/index")
    public String toEdit(@RequestParam(value = "id") String id, Model model) {
        BaseResult result = bankcardTypeService.selectAllList();
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("cardType", map.get("list"));
        }
        BaseResult cardResult = bcbBankcardService.getById(id);
        if (cardResult.isSuccess()) {
            BcbBankCardNumDetailResp data = JSONObject.parseObject(JSON.toJSONString(cardResult.getData())
                    , BcbBankCardNumDetailResp.class);
            model.addAttribute("cardNumber", data);
        }
        return "bcbBank/bcbBankcard-cardnum-edit";
    }

    /**
     * 保存银行卡
     *
     * @param bcbBankCardSaveReq
     * @return
     */
    @SysLogAop(value = "BCB银行卡保存", monitor = true)
    @PostMapping(value = "/save")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult save(@Valid @RequestBody BcbBankCardSaveReq bcbBankCardSaveReq) {
        return bcbBankcardService.save(bcbBankCardSaveReq);
    }

    @SysLogAop("跳转BCB银行卡详情页")
    @GetMapping(value = "/toDetail")
    @RequiresPermissions("/bcb/bankcard/index")
    public String toDetail(HttpServletRequest request, String id) {
        BcbBankApplyDetailReq bcbBankApplyDetailReq = new BcbBankApplyDetailReq();
        bcbBankApplyDetailReq.setId(id);
        BaseResult baseResult = bcbBankcardService.applyAuditDetail(bcbBankApplyDetailReq);
        if (baseResult.isSuccess()) {
            String jsonResult = JSONArray.toJSONString(baseResult.getData());
            BcbBankApplyAuditDetailResp detailResp = JSONObject.parseObject(jsonResult, BcbBankApplyAuditDetailResp.class);
            String updateBy = detailResp.getUpdateBy();
            if (StringUtils.isNotEmpty(updateBy)) {
                SysUser sysUser = sysUserService.selectById(updateBy);
                request.setAttribute("adminUserName", sysUser.getUsername());
            } else {
                request.setAttribute("adminUserName", "");
            }
            request.setAttribute("detail", detailResp);

        }
        return "bcbBank/bcbBankcard-detail";
    }

    /**
     * 银行卡列表
     *
     * @param req
     * @return
     */
    @SysLogAop("查询BCB银行卡列表")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult listPage(BcbBankcardListPageReq req) {
        return bcbBankcardService.listPage(req);
    }

    /**
     * 审核
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "BCB银行卡审核", monitor = true)
    @PostMapping(value = "/audit")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult audit(@RequestBody BcbBankAuditReq req) {
        BaseResult baseResult = bcbBankcardService.audit(req);
        return baseResult;
    }

    /**
     * 查看详情
     *
     * @param req
     * @return
     */
    @SysLogAop("查询BCB银行卡详情")
    @PostMapping(value = "/detail")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult detail(@RequestBody BcbBankApplyDetailReq req) {
        return bcbBankcardService.applyAuditDetail(req);
    }

    /**
     * bcb卡种管理
     *
     * @return
     */
    @SysLogAop("跳转BCB银行卡种列表")
    @GetMapping(value = "/type")
    @RequiresPermissions("/bcb/bankcard/index")
    public String bankCardIndex(Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        return "bcbBank/bcbBankcard-type";
    }

    /**
     * 卡种管理列表
     *
     * @param req
     * @return
     */
    @SysLogAop("查询BCB银行卡种列表")
    @PostMapping(value = "/type/list")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult listCardTypePage(BcbBankCardTypeReq req) {
        return bankcardTypeService.listPage(req);
    }

    /**
     * 新增卡种页面
     *
     * @return
     */
    @SysLogAop("跳转BCB银行卡种新增页")
    @GetMapping(value = "/type/add")
    @RequiresPermissions("/bcb/bankcard/index")
    public String toAddIndex(Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        model.addAttribute("viewUrl", PropertiesUtil.getString(PropertiesUtil.FILE_VIEW_URL));
        return "bcbBank/bcbBankcard-add";
    }

    /**
     * 编辑卡种页面
     *
     * @return
     */
    @SysLogAop("跳转BCB银行卡种编辑页")
    @GetMapping(value = "/type/edit")
    @RequiresPermissions("/bcb/bankcard/index")
    public String toEditIndex(BcbBankCardTypeDetailReq req, Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }

        BaseResult typeResult = bankcardTypeService.getById(req);
        if (result.isSuccess()) {
            BcbBankCardTypeDetailResp data = JSONObject.parseObject(JSON.toJSONString(typeResult.getData())
                    , BcbBankCardTypeDetailResp.class);
            model.addAttribute("cardtype", data);
        }
        model.addAttribute("viewUrl", PropertiesUtil.getString(PropertiesUtil.FILE_VIEW_URL));
        //model.addAttribute("subTypeMap", JSONObject.toJSONString(getSubType()));
        return "bcbBank/bcbBankcard-edit";
    }

    /**
     * 新增卡种
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "BCB银行卡种新增", monitor = true)
    @PostMapping(value = "/type/save")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult saveCardType(@Valid @RequestBody BcbBankCardTypeSaveReq req) {
        return bankcardTypeService.saveCardType(req);
    }

    /**
     * 上传币种图标
     *
     * @param file
     * @return
     */
    @SysLogAop(value = "上传银行卡图标", monitor = true)
    @PostMapping("/upload")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        return bankcardTypeService.upload(file);
    }

    /**
     * 删除
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "删除银行卡卡种", monitor = true)
    @PostMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("bcb:bankcard:cardlist")
    public BaseResult delete(@Valid @RequestBody BcbBankCardTypeDeleteReq req) {
        return bankcardTypeService.delete(req);
    }
}

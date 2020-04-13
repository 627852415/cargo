package com.lxtx.im.admin.web.api;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.request.DictCoinMaxListPageReq;
import com.lxtx.im.admin.service.request.DictDeleteReq;
import com.lxtx.im.admin.service.request.DictInfoReq;
import com.lxtx.im.admin.service.request.DictListPageReq;
import com.lxtx.im.admin.service.request.DictModifyValueReq;
import com.lxtx.im.admin.service.request.DictSaveReq;
import com.lxtx.im.admin.service.request.NoticeInfoResp;
import com.lxtx.im.admin.service.request.NoticeSaveReq;
import com.lxtx.im.admin.service.response.DictInfoResp;
import com.lxtx.im.admin.web.aop.SysLogAop;

/**
 * @description:  字典管理
 * @author:   CXM
 * @create:   2018-10-12 12:04
 */
@Controller
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * 字典管理主页跳转
     *
     * @return
     */
    @SysLogAop("字典管理主页跳转")
    @GetMapping(value = "/index")
    @RequiresPermissions("dict:index")
    public String toIndex() {
        return "dict/dict-index";
    }

    /**
     * 字典保存跳转页
     *
     * @return
     */
    @SysLogAop("字典保存跳转页")
    @GetMapping(value = "/add")
    @RequiresPermissions("dict:index")
    public String add() {
        return "dict/dict-save";
    }

    /**
     * 字典管理列表
     *
     * @return
     */
    @SysLogAop("字典管理列表")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("dict:index")
    public BaseResult listPage(@Valid DictListPageReq req) {
        return dictService.listPage(req);
    }

    /**
     * 根据id获取字典信息
     *
     * @param req
     * @return
     */
    @SysLogAop("获取字典信息")
    @GetMapping(value = "/info")
    @RequiresPermissions("dict:index")
    public String info(DictInfoReq req, Model model) {
        BaseResult result = dictService.info(req);
        DictInfoResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), DictInfoResp.class);
        model.addAttribute("dict", resp);
        return "dict/dict-save";
    }

    /**
     * 保存或更新字典
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "保存或更新字典", monitor = true)
    @PostMapping(value = "/save")
    @ResponseBody
    @RequiresPermissions("dict:index")
    public BaseResult saveOrUpdate(@Valid @RequestBody DictSaveReq req) {
        return dictService.saveOrUpdate(req);
    }
    /**
     * 删除字典
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "删除字典", monitor = true)
    @PostMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("dict:index")
    public BaseResult delete(@Valid @RequestBody DictDeleteReq req) {
        return dictService.delete(req);
    }

    /**
     * 币种交易通知主页跳转
     *
     * @return
     */
    @SysLogAop("币种交易通知主页跳转")
    @GetMapping(value = "/coin/notice/index")
    @RequiresPermissions("dict:index")
    public String toCoinTransactionIndex(Model model) {
        BaseResult result = dictService.transactionNoticeInfo();
        NoticeInfoResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), NoticeInfoResp.class);
        model.addAttribute("dict", resp);
        return "notice/coin-amount-max-index";
    }


    /**
     * 保存币种交易监控通知
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "保存币种交易监控通知", monitor = true)
    @PostMapping(value = "/notice/save")
    @ResponseBody
    @RequiresPermissions("dict:index")
    public BaseResult saveNotice(@Valid @RequestBody NoticeSaveReq req) {
        return dictService.saveNotice(req);
    }


    /**
     * 币种交易保存主页跳转
     *
     * @return
     */
    @SysLogAop("币种交易保存主页跳转")
    @GetMapping(value = "/transaction/coin/update")
    @RequiresPermissions("dict:index")
    public String toCoinTransactionUpdateIndex(DictInfoReq req, Model model) {
        BaseResult result = dictService.info(req);
        DictInfoResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), DictInfoResp.class);
        model.addAttribute("dict", resp);
        return "notice/coin-amount-max-save";
    }


    /**
     * 币种添加的推送跳转主页
     *
     * @return
     */
    @SysLogAop("币种添加的推送跳转主页")
    @GetMapping(value = "/notice/coin/add")
    @RequiresPermissions("dict:index")
    public String toCoinTransactionAddIndex(Model model) {
        BaseResult result = dictService.addCoinNoticeInfo();
        NoticeInfoResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), NoticeInfoResp.class);
        model.addAttribute("dict", resp);
        return "notice/coin-add-index";
    }

    /**
     * 币种交易监控保存页跳转
     *
     * @return
     */
    @SysLogAop("币种交易监控保存页跳转")
    @GetMapping(value = "/transaction/coin/add")
    @RequiresPermissions("dict:index")
    public String toAddCoinIndex() {
        return "notice/coin-amount-max-save";
    }

    /**
     * 币种交易监控列表
     *
     * @return
     */
    @SysLogAop("币种交易监控列表")
    @PostMapping(value = "/coin/list")
    @ResponseBody
    @RequiresPermissions("dict:index")
    public BaseResult listPageCoinMax(@Valid DictCoinMaxListPageReq req) {
        return dictService.listPageCoinMax(req);
    }

    /**
     * 根据domain和key修改对应的值
     * @param req
     * @return
     */
    @PostMapping("/modifyValueByDomainAndKey")
    @ResponseBody
    @SysLogAop(value = "修改字典", monitor = true)
    @RequiresPermissions("dict:index")
    public BaseResult modifyValueByDomainAndKey(@Valid DictModifyValueReq req) {
    	return dictService.modifyValueByDomainAndKey(req);
    }
}

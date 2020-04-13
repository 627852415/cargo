package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.CoinResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 币种
 *
 * @author CaiRH
 * @since 2018-08-29
 */
@Controller
@RequestMapping("/coin")
public class CoinController {

    @Autowired
    private CoinService coinService;

    /**
     * 币种列表
     *
     * @return
     */
    @SysLogAop("币种列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("coin:index")
    public String toIndex() {
        return "wallet/coin-index";
    }

    /**
     * 币种新增页
     *
     * @return
     */
    @SysLogAop("币种新增页")
    @GetMapping(value = "/add")
    @RequiresPermissions("coin:add")
    public String add() {
        return "wallet/coin-save";
    }

    /**
     * 币种保存页
     *
     * @return
     */
    @SysLogAop("币种保存页")
    @GetMapping(value = "/edit")
    @RequiresPermissions("coin:edit")
    public String edit(CoinDetailReq coinDetailReq, Model model) {
        BaseResult result = coinService.selectOne(coinDetailReq);
        CoinResp coinResp = JSONObject.parseObject(JSON.toJSONString(result.getData()), CoinResp.class);
        model.addAttribute("coin", coinResp);
        return "wallet/coin-save";
    }

    /**
     * 新增保存币种
     *
     * @param coinSaveReq
     * @param session
     * @return
     */
    @SysLogAop(value = "新增保存币种", monitor = true)
    @PostMapping(value = "/save")
    @ResponseBody
    @RequiresPermissions("coin:index")
    public BaseResult save(@Valid @RequestBody CoinSaveReq coinSaveReq, HttpSession session) {
        return coinService.save(coinSaveReq, session);
    }

    /**
     * 币种列表数据
     *
     * @param coinReq
     * @param session
     * @return
     */
    @SysLogAop("币种列表数据")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("coin:index")
    public BaseResult list(@Valid @RequestBody CoinReq coinReq, HttpSession session) {
        return coinService.listPage(coinReq, session);
    }

    /**
     * 币种列表数据
     *
     * @param coinReq
     * @param session
     * @return
     */
    @SysLogAop("法定币种列表数据")
    @PostMapping(value = "/list/legal")
    @ResponseBody
    @RequiresPermissions("coin:index")
    public BaseResult listLegal(@Valid @RequestBody CoinReq coinReq, HttpSession session) {
        return coinService.listPageLegal(coinReq, session);
    }

//    /**
//     * 删除币种
//     *
//     * @param coinDeleteReq
//     * @return
//     */
//    @SysLogAop("删除币种")
//    @PostMapping(value = "/delete")
//    @ResponseBody
//    public BaseResult delete(@Valid @RequestBody CoinDeleteReq coinDeleteReq) {
//        return coinService.delete(coinDeleteReq);
//    }

    /**
     * 上传币种图标
     *
     * @param file
     * @return
     */
    @SysLogAop(value = "上传币种图标", monitor = true)
    @PostMapping("/upload")
    @ResponseBody
    @RequiresPermissions("coin:index")
    public BaseResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        return coinService.upload(file);
    }

    /**
     * 获取币种手续费
     *
     * @param coinFeeReq
     * @return
     */
    @SysLogAop("获取币种手续费")
    @PostMapping("/fee")
    @ResponseBody
    @RequiresPermissions("coin:index")
    public BaseResult coinFee(@Valid @RequestBody CoinFeeReq coinFeeReq) {
        return coinService.obtainCoinFee(coinFeeReq);
    }

    /**
     * 获取所有显示的币种
     *
     * @param coinReq
     * @param session
     * @return
     */
    @SysLogAop("获取所有显示的币种")
    @PostMapping(value = "/listAll")
    @ResponseBody
    @RequiresPermissions("coin:index")
    public BaseResult listAll(CoinReq coinReq, HttpSession session) {
        return coinService.listAll(coinReq, session);
    }

}



package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.SdkService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.SdkThirdGameInfoResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

/**
* @description:  第三方游戏管理
* @author:   CXM
* @create:   2018-11-30 11:08
*/
@Controller
@RequestMapping("/sdk")
public class SdkController {

    @Autowired
    private SdkService sdkService;
    @Autowired
    private AirdropService airdropService;

    /**
     * 第三方游戏列表
     *
     * @return
     */
    @SysLogAop("第三方游戏首页")
    @GetMapping(value = "/game/index")
    public String toIndex() {
        return "thirdGame/third-game-index";
    }

    /**
     * 第三方游戏列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("第三方游戏列表数据")
    @PostMapping(value = "/game/list")
    @ResponseBody
    public BaseResult list(SdkThirdGameListReq req) {
        return sdkService.listPage(req);
    }

    /**
     * 第三方游戏订单
     *
     * @return
     */
    @SysLogAop("第三方游戏游戏订单")
    @GetMapping(value = "/game/order/index")
    public String orderIndex(Model model) {
        BaseResult result = airdropService.getAirdropData(new AirdropToSavePageReq());
        if (result.isSuccess()) {
            Map<String, Object> map = (Map<String, Object>) result.getData();
            model.addAttribute("coins", map.get("coins"));
        }
        return "thirdGame/third-game-order-index";
    }

    /**
     * 第三方游戏新增页
     *
     * @return
     */
    @SysLogAop("第三方游戏新增页")
    @GetMapping(value = "/game/add")
    public String add() {
        return "thirdGame/third-game-save";
    }

    /**
     * 上传游戏图标
     *
     * @param file
     * @return
     */
    @SysLogAop(value = "SDK上传游戏图标", monitor = true)
    @PostMapping("/game/upload")
    @ResponseBody
    public BaseResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        return sdkService.upload(file);
    }

    /**
     * 第三方游戏订单列表
     *
     * @param req
     * @return
     */
    @SysLogAop("第三方游戏订单列表")
    @PostMapping(value = "/game/order/list")
    @ResponseBody
    public BaseResult orderList(SdkThirdGameOrderListReq req) {
        return sdkService.orderListPage(req);
    }

    /**
     * 新增第三方游戏
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "新增第三方游戏", monitor = true)
    @PostMapping(value = "/game/save")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody SdkSaveThirdGameReq req) {
        return sdkService.save(req);
    }

    /**
     * 第三方游戏订单审核
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "第三方游戏订单审核", monitor = true)
    @PostMapping(value = "/game/order/audit")
    @ResponseBody
    public BaseResult orderAudit(SdkThirdGameOrderAuditReq req) {
        return sdkService.orderAudit(req);
    }

    /**
     * 根据id获取游戏信息
     *
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("第三方游戏获取游戏信息")
    @GetMapping(value = "/game/info")
    public String gameInfo(SdkThirdGameInfoReq req, Model model) {
        BaseResult result = sdkService.gameInfo(req);
        SdkThirdGameInfoResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), SdkThirdGameInfoResp.class);
        model.addAttribute("game", resp);
        return "thirdGame/third-game-save";
    }

    /**
     * 删除游戏
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "删除游戏", monitor = true)
    @PostMapping(value = "/game/delete")
    @ResponseBody
    public BaseResult delete(@Valid @RequestBody SdkThirdGameDeleteReq req) {
        return sdkService.delete(req);
    }

    /**
     * 是否启用游戏
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "是否启用游戏", monitor = true)
    @PostMapping(value = "/game/update/status")
    @ResponseBody
    public BaseResult updateGameStatus(@Valid @RequestBody SdkUpdateThirdGameStatusReq req) {
        return sdkService.updateGameStatus(req);
    }

}



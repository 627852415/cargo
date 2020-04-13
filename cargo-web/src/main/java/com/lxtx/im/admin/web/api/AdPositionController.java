package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AdPositionService;
import com.lxtx.im.admin.service.request.AdPositionDetailByIdReq;
import com.lxtx.im.admin.service.request.AdPositionListReq;
import com.lxtx.im.admin.service.request.AdPositionSaveReq;
import com.lxtx.im.admin.service.request.StickerSaveReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;

/**
 * 广告位
 *
 * @author xufeifei
 */
@Controller
@RequestMapping("/ad/position")
public class AdPositionController {

    @Autowired
    private AdPositionService adPositionService;

    /**
     * 广告位列表
     *
     * @return
     */
    @SysLogAop("跳转广告位列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("ad:position:index")
    public String toIndex() {
        return "adPosition/ad-position-index";
    }

    /**
     * 广告位列表数据
     *
     * @param adPositionListReq
     * @return
     */
    @SysLogAop("查询广告位列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("ad:position:index")
    public BaseResult listPage(AdPositionListReq adPositionListReq) {
        return adPositionService.listPage(adPositionListReq);
    }

    /**
     * 查看详情
     *
     * @param adPositionDetailByIdReq
     * @return
     */
    @SysLogAop("查询广告位详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("ad:position:index")
    public String detail(AdPositionDetailByIdReq adPositionDetailByIdReq,Model model) {
        adPositionService.detail(adPositionDetailByIdReq,model);
        return "adPosition/ad-position-detail";
    }

    /**
     * 新增页面
     */
    @SysLogAop(value = "广告位新增页面",monitor = false)
    @GetMapping(value = "/toAdd")
    @RequiresPermissions("ad:position:toAdd")
    public String toAdd() {
        return "adPosition/ad-position-add";
    }


    @SysLogAop(value = "广告位保存",monitor = false)
    @PostMapping(value = "/save")
    @RequiresPermissions("ad:position:toAdd")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody AdPositionSaveReq req) {
        return adPositionService.save(req);
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop("广告位编辑")
    @GetMapping(value = "/edit")
    @RequiresPermissions("ad:position:edit")
    public String edit(AdPositionDetailByIdReq saveReq, Model model) {
        adPositionService.detail(saveReq, model);
        return "adPosition/ad-position-add";
    }

    @SysLogAop(value = "广告位删除操作", monitor = false)
    @PostMapping(value = "/del")
    @RequiresPermissions("ad:position:del")
    @ResponseBody
    public BaseResult del(@Valid @RequestBody AdPositionDetailByIdReq req) {
        return adPositionService.deleteById(req);
    }
}

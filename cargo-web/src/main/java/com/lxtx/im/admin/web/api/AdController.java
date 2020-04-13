package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.AdService;
import com.lxtx.im.admin.service.request.AdDetailByIdReq;
import com.lxtx.im.admin.service.request.AdListReq;
import com.lxtx.im.admin.service.request.AdPositionSaveReq;
import com.lxtx.im.admin.service.request.AdSaveReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


/**
 * 广告投放
 *
 * @author map
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    /**
     * 广告列表
     *
     * @return
     */
    @SysLogAop("跳转广告列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("ad:index")
    public String toIndex() {
        return "ad/ad-index";
    }

    /**
     * 广告列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("查询广告列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("ad:index")
    public BaseResult listPage(AdListReq req) {
        return adService.listPage(req);
    }

    /**
     * 新增页面
     */
    @SysLogAop(value = "广告新增页面",monitor = false)
    @GetMapping(value = "/toAdd")
    @RequiresPermissions("ad:index")
    public String toAdd(Model model,String id) {
        adService.add(model,id);
        return "ad/ad-add";
    }


    @SysLogAop(value = "广告保存",monitor = false)
    @PostMapping(value = "/save")
    @RequiresPermissions("ad:index")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody AdSaveReq req) {
        return adService.save(req);
    }

    /**
     * 跳转到编辑页面
     */
    @SysLogAop("广告编辑")
    @GetMapping(value = "/edit")
    @RequiresPermissions("ad:index")
    public String edit(AdDetailByIdReq req, Model model) {
        adService.detail(req, model);
        return "ad/ad-add";
    }

    /**
     * 上传广告位素材
     *
     * @param file
     * @return
     */
    @SysLogAop("上传广告位素材")
    @PostMapping("/upload")
    @ResponseBody
    public BaseResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        return adService.upload(file);
    }

    /**
     * 跳转到详情页面
     */
    @SysLogAop("广告详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("ad:index")
    public String detail(AdDetailByIdReq req, Model model) {
        adService.detail(req, model);
        return "ad/ad-detail";
    }
}

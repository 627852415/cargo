package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.StickerService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.StickerDetailByIdResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedList;

/**
 * 表情包
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Controller
@RequestMapping("/sticker")
public class StickerController {

    @Autowired
    private StickerService stickerService;

    /**
     * 首页
     */
    @SysLogAop("表情包首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("sticker:index")
    public String index() {
        return "sticker/sticker-index";
    }

    /**
     * 列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author
     * @date 2018/12/14
     */
    @SysLogAop("表情包列表")
    @PostMapping(value = "/list")
    @RequiresPermissions("sticker:index")
    @ResponseBody
    public BaseResult listPage(StickerListReq req) {
        return stickerService.listPage(req);
    }

    /**
     * 新增页面
     */
    @SysLogAop("表情包新增页面")
    @GetMapping(value = "/toAdd")
    @RequiresPermissions("sticker:toAdd")
    public String toAdd() {
        return "sticker/sticker-add";
    }

    /**
     * 新增
     */
    @SysLogAop(value = "表情包新增", monitor = true)
    @PostMapping(value = "/add")
    @RequiresPermissions("sticker:index")
    @ResponseBody
    public BaseResult add(@RequestParam("file") LinkedList<MultipartFile> file, @Valid StickerSaveReq req) {
        return stickerService.add(file, req);
    }

    /**
     * 更新表情包
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @SysLogAop(value = "更新表情包", monitor = true)
    @PostMapping(value = "/update")
    @RequiresPermissions("sticker:update")
    @ResponseBody
    public BaseResult update(@Valid @RequestBody StickerUpdateReq req) {
        return stickerService.update(req);
    }

    /**
     * 删除表情包
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @SysLogAop(value = "删除表情包", monitor = true)
    @PostMapping(value = "/delete")
    @RequiresPermissions("sticker:delete")
    @ResponseBody
    public BaseResult delete(@Valid @RequestBody StickerDeleteReq req) {
        return stickerService.delete(req);
    }

    /**
     * 表情包详情跳转
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @SysLogAop("表情包详情跳转")
    @GetMapping(value = "/jump/edit")
    @RequiresPermissions("sticker:jump:edit")
    public String toEdit(StickerDetailReq req, Model model) {
        BaseResult result = stickerService.detail(req);
        StickerDetailByIdResp resp = JSONObject.parseObject(JSON.toJSONString(result.getData()), StickerDetailByIdResp.class);
        model.addAttribute("sticker", resp);
        return "sticker/sticker-edit";
    }

    /**
     * 表情包里的表情列表
     *
     * @param req
     * @author CXM
     * @date 2018/12/24
     */
    @SysLogAop("表情包里的表情列表")
    @PostMapping(value = "/detail/list")
    @RequiresPermissions("sticker:index")
    @ResponseBody
    public BaseResult detailListPage(StickerDetailListReq req) {
        return stickerService.detailListPage(req);
    }

    /**
     * 更新表情内容
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @SysLogAop(value = "更新表情内容", monitor = true)
    @PostMapping(value = "/update/detail")
    @RequiresPermissions("sticker:index")
    @ResponseBody
    public BaseResult updateDetail(@Valid @RequestBody StickerUpdateDetailReq req) {
        return stickerService.updateDetail(req);
    }

    /**
     * 表情包封面图片上传
     *
     * @param  file
     * @return
     * @author  CXM
     * @date   2018-12-25 17:20
     */
    @SysLogAop(value = "表情包封面图片上传", monitor = true)
    @PostMapping(value = "/upload/cover")
    @RequiresPermissions("sticker:index")
    @ResponseBody
    public BaseResult uploadCover(@RequestParam("file") MultipartFile file) throws IOException {
        return stickerService.uploadCover(file);
    }
}

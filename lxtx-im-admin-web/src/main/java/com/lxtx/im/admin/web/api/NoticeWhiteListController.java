package com.lxtx.im.admin.web.api;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.NoticeWhitelistService;
import com.lxtx.im.admin.service.request.NoticeWhiteListDeleteReq;
import com.lxtx.im.admin.service.request.NoticeWhiteListListReq;
import com.lxtx.im.admin.service.request.NoticeWhiteListSaveReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/notice/whitelist")
public class NoticeWhiteListController {

    @Autowired
    private NoticeWhitelistService noticeWhitelistService;

    /*
     *主页
     */
    @GetMapping("/index")
    public String index() {
        return "whitelist/notice-index";
    }

    /*
     *主页
     */
    @GetMapping("/tosave")
    public String toSave() {
        return "whitelist/notice-save";
    }


    @PostMapping("/page/list")
    @ResponseBody
    public BaseResult orderList(@Valid NoticeWhiteListListReq req) {
        return noticeWhitelistService.listPage(req);
    }

    @PostMapping("/save")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody NoticeWhiteListSaveReq req) {
        return noticeWhitelistService.save(req);
    }

    @PostMapping("/delete")
    @ResponseBody
    public BaseResult save(@Valid @RequestBody NoticeWhiteListDeleteReq req) {
        return noticeWhitelistService.delete(req);
    }

}


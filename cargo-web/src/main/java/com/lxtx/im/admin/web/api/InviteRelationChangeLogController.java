package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.InviteRelationChangeLogService;
import com.lxtx.im.admin.service.request.InviteChangeLogListPageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 邀请关系转移
 *
 * @Author: liyunhua
 * @Date: 2019/4/1
 */
@Controller
@RequestMapping("/invite/relation/change/log")
public class InviteRelationChangeLogController {


    @Autowired
    private InviteRelationChangeLogService inviteRelationChangeLogService;


    /**
     * 邀请关系转移日志首页
     */
    @SysLogAop("邀请关系转移日志首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("invite:relation:change:log:index")
    public String index() {
        return "inviteRelation/invite-relation-change-log";
    }

    /**
     * 列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("邀请关系转移日志列表数据")
    @PostMapping(value = "/list/page")
    @ResponseBody
    @RequiresPermissions("invite:relation:change:log:index")
    public BaseResult listPage(InviteChangeLogListPageReq req) {
        return inviteRelationChangeLogService.listPage(req);
    }


}

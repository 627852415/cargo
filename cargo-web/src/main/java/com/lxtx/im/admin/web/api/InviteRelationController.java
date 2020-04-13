package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.InviteRelationService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 邀请关系管理
 *
 * @Author: liyunhua
 * @Date: 2019/1/11
 */
@Controller
@RequestMapping("/inviteRelation")
public class InviteRelationController {


    @Autowired
    private InviteRelationService inviteRelationService;

    /**
     * 下级各级人数列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("下级各级人数列表")
    @PostMapping(value = "/eachLevelNumList")
    @ResponseBody
    @RequiresPermissions("inviteRelation:index")
    public BaseResult eachLevelNumList(ViewRelationReq req) {
        return inviteRelationService.eachLevelNumList(req);
    }

    /**
     * 直属上级列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("直属上级列表")
    @PostMapping(value = "/higherList")
    @ResponseBody
    @RequiresPermissions("inviteRelation:index")
    public BaseResult higherList(ViewRelationReq req) {
        return inviteRelationService.higherList(req);
    }

    /**
     * 直属下级列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("直属下级列表")
    @PostMapping(value = "/lowerList")
    @ResponseBody
    @RequiresPermissions("inviteRelation:index")
    public BaseResult lowerList(ViewRelationPageReq req) {
        return inviteRelationService.lowerList(req);
    }


    /**
     * 查看邀请关系链
     */
    @SysLogAop("查看邀请关系链")
    @GetMapping(value = "/relation")
    @RequiresPermissions("inviteRelation:index")
    public String viewRelation(Model model, InviteRelationReq req) {
        model.addAttribute("account", req.getAccount());
        return "inviteRelation/view-relation-index";
    }


    /**
     * 邀请关系管理首页
     */
    @SysLogAop("邀请关系管理首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("inviteRelation:index")
    public String index() {
        return "inviteRelation/invite-relation-index";
    }

    /**
     * 邀请注册直系关系首页
     */
    @SysLogAop("邀请注册直系关系首页")
    @GetMapping(value = "/direct/index")
    @RequiresPermissions("inviteRelation:index")
    public String direct() {
        return "inviteRelation/direct-relation-index";
    }

    /**
     * 邀请注册直系关系列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("邀请注册直系关系列表")
    @PostMapping(value = "/direct/list")
    @ResponseBody
    @RequiresPermissions("inviteRelation:index")
    public BaseResult directList(InviteDirectRelationReq req) {
        return inviteRelationService.directList(req);
    }


    /**
     * 检查下级用户是否已有上级帐号
     *
     * @param inviteRelationReq
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/1/14
     */
    @SysLogAop("检查下级用户是否已有上级帐号")
    @PostMapping(value = "/checkRelation")
    @ResponseBody
    @RequiresPermissions("inviteRelation:index")
    public BaseResult checkInviteRelation(InviteRelationReq inviteRelationReq) {
        return inviteRelationService.checkInviteRelation(inviteRelationReq);
    }

    /**
     * 新增关系(添加下级)
     *
     * @param inviteRelationReq
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/1/14
     */
    @SysLogAop(value = "新增关系(添加下级)", monitor = true)
    @PostMapping(value = "/addRelation")
    @ResponseBody
    @RequiresPermissions("inviteRelation:addRelation")
    public BaseResult addRelation(InviteRelationReq inviteRelationReq) {
        return inviteRelationService.addRelation(inviteRelationReq);
    }

    /**
     * 邀请关系管理列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("邀请关系管理列表")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("inviteRelation:index")
    public BaseResult listPage(InviteRelationListPageReq req) {
        return inviteRelationService.listPage(req);
    }

    /**
     * 导出excel文档
     *
     * @param response, req
     * @return void
     * @author liyunhua
     * @date 2018/12/20
     */
    @SysLogAop(value = "导出邀请关系列表", monitor = true)
    @GetMapping(value = "/exportExcel")
    @RequiresPermissions("inviteRelation:index")
    public void exportExcel(HttpServletResponse response, InviteRelationListPageReq req) {
        inviteRelationService.exportExcel(response, req);
    }

    /**
     * 查看用户绑定关系链
     */
    @SysLogAop("查看用户绑定关系链")
    @GetMapping(value = "/bind/relation")
    @RequiresPermissions("inviteRelation:index")
    public String bindRelation(Model model, InviteRelationReq req) {
        return "inviteRelation/bind-relation-index";
    }

    /**
     * 查询绑定关系
     * 直接上级，只查上面一个
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("查询绑定关系/上级")
    @PostMapping(value = "/directHigher")
    @ResponseBody
    @RequiresPermissions("inviteRelation:index")
    public BaseResult directHigher(InviteRelationListPageReq req) {
        return inviteRelationService.directHigher(req);
    }

    /**
     * 查询绑定关系
     * 所有绑定下级列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018/12/14
     */
    @SysLogAop("查询绑定关系/下级")
    @PostMapping(value = "/bind/lowerList")
    @ResponseBody
    @RequiresPermissions("inviteRelation:index")
    public BaseResult bindLowerList(InviteRelationListPageReq req) {
        return inviteRelationService.bindLowerList(req);
    }

    /**
     * 转移绑定关系
     *
     * @param relationChangeReq
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/1/14
     */
    @SysLogAop(value = "转移绑定关系", monitor = true)
    @PostMapping(value = "/relationChange")
    @ResponseBody
    @RequiresPermissions("inviteRelation:relationChange")
    public BaseResult relationChange(RelationChangeReq relationChangeReq) {
        return inviteRelationService.relationChange(relationChangeReq);
    }

}

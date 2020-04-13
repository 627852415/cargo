package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.GroupService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 群组控制器
 *
 * @Author liyunhua
 * @Date 2018-09-11 0011
 */
@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 群组管理首页
     *
     * @param
     * @return java.lang.String
     * @author liyunhua
     * @date 2018-08-30 0030
     */
    @SysLogAop("群组管理首页")
    @GetMapping("/index")
    @RequiresPermissions("group:list")
    public String index() {
        return "group/group-index";
    }

    /**
     * 搜索群组列表
     *
     * @param groupListPageReq, session
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-08-30 0030
     */
    @SysLogAop("搜索群组列表")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("group:list")
    public BaseResult listPage(GroupListPageReq groupListPageReq, HttpSession session) {
        return groupService.listPage(groupListPageReq);
    }

    /**
     * 解散群
     *
     * @param groupDisbandReq
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-12-04 0004
     */
    @SysLogAop(value = "解散群", monitor = true)
    @PostMapping(value = "/disband")
    @ResponseBody
    @RequiresPermissions("disband:group")
    public BaseResult disband(@RequestBody GroupDisbandReq groupDisbandReq) {
        return groupService.disband(groupDisbandReq);
    }

    /**
     * 网易云信群同步
     *
     * @date 2018-08-30 0030
     */
    @SysLogAop(value = "网易云信群同步", monitor = true)
    @PostMapping(value = "/synchronize/yunxin")
    @ResponseBody
    @RequiresPermissions("group:list")
    public BaseResult synchronizeYunxinGroup() {
        return groupService.synchronizeYunxinGroup();
    }

    /**
     * 修改群私聊权限
     *
     * @date 2018-11-23
     */
    @SysLogAop(value = "修改群私聊权限", monitor = true)
    @PostMapping(value = "/information/flag")
    @ResponseBody
    @RequiresPermissions("group:information:flag")
    public BaseResult updateInformationFlag(@RequestBody GroupAlterInformationReq groupAlterInformationReq) {
        return groupService.updateInformationFlag(groupAlterInformationReq);
    }

    @SysLogAop("群组活跃度列表")
    @GetMapping("/active/index")
    @RequiresPermissions("group:list")
    public String activeIndex() {
        return "group/group-active-index";
    }


    /**
     * 搜索群活跃度列表
     *
     * @param groupActiveListReq, session
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liboyan
     * @date 2018-08-30 0030
     */
    @SysLogAop("群组活跃度列表数据")
    @PostMapping(value = "/groupActiveList")
    @ResponseBody
    @RequiresPermissions("group:list")
    public BaseResult groupActiveList(GroupActiveListReq groupActiveListReq, HttpSession session) {
        return groupService.groupActiveList(groupActiveListReq);
    }

    /**
     * 群游戏列表首页
     */
    @SysLogAop("群游戏列表首页")
    @GetMapping(value = "/game/index")
    @RequiresPermissions("group:list")
    public String gameIndex() {
        return "group/group-game-index";
    }

    /**
     * 群游戏列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/02/26
     */
    @SysLogAop("群游戏列表数据")
    @PostMapping(value = "/game/listPage")
    @ResponseBody
    @RequiresPermissions("group:list")
    public BaseResult listPage(GroupGameListPageReq req) {
        return groupService.gameListPage(req);
    }


    /**
     * 结束游戏
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/2/27
     */
    @SysLogAop(value = "结束游戏", monitor = true)
    @PostMapping(value = "/game/stop")
    @ResponseBody
    @RequiresPermissions("group:game:stop")
    public BaseResult gameStop(@RequestBody GroupGameStopReq req) {
        return groupService.gameStop(req);
    }

    /**
     * 取消游戏排队
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/2/27
     */
    @SysLogAop(value = "取消游戏排队", monitor = true)
    @PostMapping(value = "/game/cancel")
    @ResponseBody
    @RequiresPermissions("group:list")
    public BaseResult gameCancel(@RequestBody GroupGameCancelReq req) {
        return groupService.gameCancel(req);
    }

    /**
     * 功能描述: 系统自动生成头像一键替换
     * 
     * @param: req
     * @return: 
     * @author: Czh
     * @date: 2019-05-17 16:39
     */
    @SysLogAop(value = "系统自动生成头像一键替换", monitor = true)
    @PostMapping("/icon/replace")
    @ResponseBody
    @RequiresPermissions("group:list")
    public BaseResult replaceGroupIoc(@RequestBody ReplaceGroupIocReq req) {
        return groupService.replaceGroupIoc(req);
    }

    /**
     * 列表导出
     */
    @SysLogAop(value = "群数据导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("group:list")
    public void downloadList(HttpServletResponse response, GroupListPageReq groupListPageReq) {
        groupService.downloadList(response, groupListPageReq);
    }
}

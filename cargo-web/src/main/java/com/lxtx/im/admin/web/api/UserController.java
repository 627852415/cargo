package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     *
     * @param
     * @return
     */
    @SysLogAop("用户列表首页")
    @GetMapping("/index")
    @RequiresPermissions("user:index")
    public String index() {
        return "user/user-index";
    }

    /**
     * 获取用户列表
     *
     * @param req
     * @return
     */
    @SysLogAop("获取用户列表数据")
    @PostMapping("/listPage")
    @RequiresPermissions("user:index")
    @ResponseBody
    public BaseResult listPage(UserListPageReq req) {
        return userService.listPage(req);
    }


    /**
     * @param req
     * @param session
     * @return
     * @Description 获取IM用户导出锁
     */
    @SysLogAop(value = "获取IM用户导出锁")
    @PostMapping(value = "/downloadLock")
    @ResponseBody
//    @RequiresPermissions("transaction:friends:index")
    public BaseResult downloadLockUser(UserListPageReq req, HttpSession session) {
        return userService.downloadLock(req, session);
    }

    /**
     * @param response
     * @param req
     * @Description 钱包转账列表导出
     */
    @SysLogAop(value = "钱包转账列表导出")
    @GetMapping(value = "/download/list")
//    @RequiresPermissions("transaction:wallet:index")
    public void downloadListUser(UserListPageReq req, HttpSession session, HttpServletResponse response) {
        userService.downloadList(req, session, response);
    }

    /**
     * 停用或禁用账号
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "停用或禁用账号", monitor = true)
    @PostMapping("/operateState")
    @RequiresPermissions("im:user:operate")
    @ResponseBody
    public BaseResult operateState(UserStateOperateReq req) {
        return userService.operateState(req);
    }

    /**
     * 重置密码
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "重置密码", monitor = true)
    @PostMapping("/resetPsd")
    @RequiresPermissions("im:user:reset:password")
    @ResponseBody
    public BaseResult resetPsd(UserResetPsdReq req) {
        return userService.resetPsd(req);
    }


    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "修改用户信息", monitor = true)
    @PostMapping("/modify")
    @RequiresPermissions("im:user:modify")
    @ResponseBody
    public BaseResult modify(UserModifyReq req) {
        return userService.modify(req);
    }

    /**
     * 获取最新的各个国家手机区号列表
     *
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-08-17 0017
     */
    @SysLogAop("获取最新的各个国家手机区号列表")
    @PostMapping("/global/code")
    @RequiresPermissions("user:index")
    @ResponseBody
    public BaseResult getGlobalCodeList() {
        return userService.getGlobalCodeList();
    }

    /**
     * IM用户同步到网易云信
     *
     * @return
     */
    @SysLogAop(value = "IM用户同步到网易云信", monitor = true)
    @PostMapping("/synchronize/yunxin")
    @RequiresPermissions("user:index")
    @ResponseBody
    public BaseResult synchronizeYunxinUser(UserYXSyncReq req) {
        return userService.synchronizeYunxinUser(req);
    }

    /**
     * 查看用户所有群组
     *
     * @param model, userReq
     * @return java.lang.String
     * @author liyunhua
     * @date 2019/1/8
     */
    @SysLogAop("查看用户所有群组")
    @GetMapping(value = "/group")
    @RequiresPermissions("user:index")
    public String detail(Model model, UserReq userReq) {
        model.addAttribute("account", userReq.getAccount());
        return "group/group-index";
    }

    /**
     * 功能描述: 初始化agora uid
     *
     * @return
     * @author Czh
     * @date 2019/12/5 11:31 上午
     */
    @SysLogAop("初始化agoraUid")
    @PostMapping("/init/agora")
    @RequiresPermissions("user:index")
    @ResponseBody
    public BaseResult initAgoraUid(){
        return userService.initAgoraUid();
    }

    /**
     *
     * 功能描述: 踢用户下线
     *
     * @param
     * @return
     * @author liboyan
     * @date 2020-02-26 10:22
     */
    @SysLogAop(" 踢用户下线")
    @PostMapping("/kickUserOffline")
    @RequiresPermissions("user:kickUserOffline")
    @ResponseBody
    public BaseResult kickUserOffline(UserReq userReq){
        return userService.kickUserOffline(userReq);
    }

    @SysLogAop("初始化userUid")
    @PostMapping("/init/userid")
    @RequiresPermissions("user:index")
    @ResponseBody
    public BaseResult initUserUid(){
        return userService.initUserUid();
    }

}

package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CommissionUserService;
import com.lxtx.im.admin.service.request.CommissionUserDetailReq;
import com.lxtx.im.admin.service.request.CommissionUserListReq;
import com.lxtx.im.admin.service.request.CommissionUserUpdateReq;
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
 * 返佣伙伴
 *
 * @author xufeifei
 */
@Controller
@RequestMapping("/commission/user")
public class CommissionUserController {

    @Autowired
    private CommissionUserService commissionUserService;

    /**
     * 返佣伙伴列表
     *
     * @return
     */
    @SysLogAop("跳转返佣伙伴列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("commission:user:index")
    public String toIndex() {
        return "commissionUser/commission-user-index";
    }

    /**
     * 返佣伙伴列表
     *
     * @param commissionUserListReq
     * @return
     */
    @SysLogAop("查询返佣伙伴列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("commission:user:index")
    public BaseResult listPage(CommissionUserListReq commissionUserListReq) {
        return commissionUserService.listPage(commissionUserListReq);
    }

    /**
     * 查看详情
     *
     * @param req
     * @return
     */
    @SysLogAop("查询返佣伙伴详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("commission:user:index")
    public String detail(CommissionUserDetailReq req, Model model) {
        commissionUserService.detail(req,model);
        return "commissionUser/commission-user-detail";
    }

    /**
     * 修改用户返佣权限

     * @param  req
     * @return
     */
    @SysLogAop(value = "修改用户返佣权限", monitor = true)
    @PostMapping(value = "/update")
    @RequiresPermissions("commission:user:index")
    @ResponseBody
    public BaseResult update(@Valid @RequestBody CommissionUserUpdateReq req) {
        return commissionUserService.update(req);
    }

    /**
     *
     * @Description 获取返佣用户列表导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取OTC交易记录导出锁")
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("commission:user:index")
    public BaseResult downloadLock(CommissionUserListReq req, HttpSession session) {
        return commissionUserService.downloadLock(req,session);
    }

    /**
     * 列表导出
     */
    @SysLogAop(value = "交易记录列表导出", monitor = true)
    @GetMapping(value = "/download/list")
    @RequiresPermissions("commission:user:index")
    public void downloadList(HttpServletResponse response, HttpSession session, CommissionUserListReq req) {
        commissionUserService.downloadList(response, session, req);
    }

    /**
     * 广告地址修改通知
     *
     * @return
     */
    @SysLogAop("广告地址修改通知")
    @PostMapping(value = "/ad/notify")
    @ResponseBody
    @RequiresPermissions("commission:user:index")
    public BaseResult adNotify() {
        return commissionUserService.adNotify();
    }
}

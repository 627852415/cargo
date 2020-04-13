package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.FeignAdminReceiveListReq;
import com.lxtx.im.admin.feign.request.FeignAdminSendListReq;
import com.lxtx.im.admin.service.RedPacketService;
import com.lxtx.im.admin.service.request.AdminReceiveListReq;
import com.lxtx.im.admin.service.request.AdminSendListReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xumf
 * @since 2019/11/27
 */
@Controller
@RequestMapping("/red/packet")
public class RedPacketController {

    @Autowired
    private RedPacketService redPacketService;

    @SysLogAop("跳转交易流水列表")
    @GetMapping(value = "/send/index")
    @RequiresPermissions("transaction:red:packet:send:index")
    public String toIndexSend() {
        return "transaction/send-red-packet-index";
    }

    @SysLogAop("跳转交易流水详情")
    @GetMapping(value = "/send/detail")
    @RequiresPermissions("transaction:red:packet:send:index")
    public String toIndexSendDetail(@RequestParam String id, Model model) {
        return redPacketService.sendDetail(id, model);
    }

    @SysLogAop("跳转交易流水列表")
    @GetMapping(value = "/receive/index")
    @RequiresPermissions("transaction:red:packet:receive:index")
    public String toIndexReceive() {
        return "transaction/receive-red-packet-index";
    }

    @SysLogAop("跳转交易流水详情")
    @GetMapping(value = "/receive/detail")
    @RequiresPermissions("transaction:red:packet:receive:index")
    public String toIndexReceiveDetail(@RequestParam String id, Model model) {
        return redPacketService.receiveDetail(id, model);
    }

    /**
     * 红包发送交易记录
     *
     * @param req {@link AdminSendListReq}
     * @return {@link BaseResult}
     */
    @PostMapping("/admin/send/list")
    @ResponseBody
    @RequiresPermissions("transaction:red:packet:send:index")
    public BaseResult adminSendList(AdminSendListReq req) {

        FeignAdminSendListReq feignAdminSendListReq = redPacketService.getFeignAdminSendListReq(req);
        return redPacketService.adminSendList(feignAdminSendListReq);
    }

    /**
     * 红包领取交易记录
     *
     * @param req {@link AdminReceiveListReq}
     * @return {@link BaseResult}
     */
    @PostMapping("/admin/receive/list")
    @ResponseBody
    @RequiresPermissions("transaction:red:packet:receive:index")
    public BaseResult adminReceiveList(AdminReceiveListReq req) {

        FeignAdminReceiveListReq feignAdminSendListReq = redPacketService.getFeignAdminReceiveListReq(req);
        return redPacketService.adminReceiveList(feignAdminSendListReq);
    }

    /**
     * 获取红包发送列表导出锁
     */
    @SysLogAop(value = "获取红包发送列表导出锁")
    @PostMapping(value = "/send/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:red:packet:send:index")
    public BaseResult downloadLockSend(AdminSendListReq req, HttpSession session) {
        return redPacketService.sendDownloadLock(req, session);
    }

    /**
     * 红包发送列表导出
     */
    @SysLogAop(value = "红包发送列表导出")
    @GetMapping(value = "/send/download/list")
    @RequiresPermissions("transaction:red:packet:send:index")
    public void downloadListSend(AdminSendListReq req, HttpSession session, HttpServletResponse response) {
        redPacketService.sendDownloadList(req, session, response);
    }


    /**
     * 获取红包发送列表导出锁
     */
    @SysLogAop(value = "获取红包发送列表导出锁")
    @PostMapping(value = "/receive/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:red:packet:receive:index")
    public BaseResult downloadLockReceive(AdminReceiveListReq req, HttpSession session) {
        return redPacketService.receiveDownloadLock(req, session);
    }

    /**
     * 红包发送列表导出
     */
    @SysLogAop(value = "红包发送列表导出")
    @GetMapping(value = "/receive/download/list")
    @RequiresPermissions("transaction:red:packet:receive:index")
    public void downloadListReceive(AdminReceiveListReq req, HttpSession session, HttpServletResponse response) {
        redPacketService.receiveDownloadList(req, session, response);
    }
}

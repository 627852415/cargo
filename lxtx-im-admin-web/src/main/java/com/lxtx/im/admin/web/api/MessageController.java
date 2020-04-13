package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.MessageService;
import com.lxtx.im.admin.service.request.DeleteMessageReq;
import com.lxtx.im.admin.service.request.MessageListReq;
import com.lxtx.im.admin.service.request.SendMessageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 发送消息
 * @author: CXM
 * @create: 2018-09-05 16:25
 **/
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     *向在线用户发送消息
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "向在线用户发送消息", monitor = true)
    @PostMapping("/send")
    @ResponseBody
    @RequiresPermissions("message:index")
    public BaseResult send(SendMessageReq req){
        return messageService.send(req);
    }

    @SysLogAop(value = "删除消息", monitor = true)
    @PostMapping("/delete")
    @ResponseBody
    @RequiresPermissions("message:index")
    public BaseResult delete(DeleteMessageReq req) throws Exception {
        return messageService.delete(req);
    }

    @SysLogAop("消息列表")
    @PostMapping("/list")
    @ResponseBody
    @RequiresPermissions("message:index")
    public BaseResult listPage(MessageListReq req) throws Exception {
        return messageService.listPage(req);
    }

    @SysLogAop("dataMigration")
    @PostMapping("/dataMigration")
    @ResponseBody
    @RequiresPermissions("message:index")
    public BaseResult dataMigration() throws Exception {
        return messageService.dataMigration();
    }

    @SysLogAop("消息首页")
    @GetMapping("/index")
    @RequiresPermissions("message:index")
    public String index(){
        return "message/message-index";
    }

}

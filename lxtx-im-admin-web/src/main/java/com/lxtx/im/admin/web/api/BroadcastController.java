package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.BroadcastService;
import com.lxtx.im.admin.service.request.BroadcastPushReq;
import com.lxtx.im.admin.service.request.SendBroadcastSwitchMessageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author CaiRH
 */
@Controller
@RequestMapping("/broadcast")
public class BroadcastController {

    @Autowired
    private BroadcastService broadcastService;

    /**
     * 广播首页
     *
     * @return
     */
    @SysLogAop("跳转广播首页")
    @GetMapping("/index")
    @RequiresPermissions("broadcast:index")
    public String index(ModelMap map) {
        broadcastService.index(map);
        return "broadcast/broadcast-index";
    }

    /**
     * 发送广播
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "发送广播", monitor = true)
    @PostMapping("/push")
    @ResponseBody
    @RequiresPermissions("broadcast:index")
    public BaseResult push(@Valid @RequestBody BroadcastPushReq req) throws Exception {
        return broadcastService.push(req);
    }
    /**
     *
     * 功能描述: 向所有在线用户发送离线消息
     *
     * @param
     * @return
     * @author liboyan
     * @date 2018-11-13 15:45
     */
    @SysLogAop(value = "向所有在线用户发送离线消息", monitor = true)
    @PostMapping("/all/offline/msg")
    @ResponseBody
    @RequiresPermissions("broadcast:index")
    public BaseResult sendBroadcastOfflineMessage() throws Exception {
        return broadcastService.sendBroadcastOfflineMessage();
    }


    @SysLogAop(value = "向所有用户发送开关", monitor = true)
    @PostMapping("/all/switch/msg")
    @ResponseBody
    @RequiresPermissions("broadcast:index")
    public BaseResult sendBroadcastSwitchMessage(@Valid @RequestBody SendBroadcastSwitchMessageReq req) throws Exception {
        return broadcastService.sendBroadcastSwitchMessage(req);
    }

    /**
     * 广播指令首页
     *
     * @return
     */
    @SysLogAop("跳转广播指令首页")
    @GetMapping("/order")
    @RequiresPermissions("broadcast:index")
    public String order() {
        return "broadcast/broadcast-order";
    }


}

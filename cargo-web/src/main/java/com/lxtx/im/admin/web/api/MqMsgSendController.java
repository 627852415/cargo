package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.MqMsgSendService;
import com.lxtx.im.admin.service.enums.EnumMqMsgSendType;
import com.lxtx.im.admin.service.request.MqMsgSendCancelReq;
import com.lxtx.im.admin.service.request.MqMsgSendListPageReq;
import com.lxtx.im.admin.service.request.MqMsgSendRetryReq;
import com.lxtx.im.admin.service.response.EnumDataStructureResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mq消息发送重试管理
 *
 * @author : CaiRH
 * @since : 2020-01-03
 */
@Controller
@RequestMapping("/mq/msg/send")
public class MqMsgSendController {

    @Autowired
    private MqMsgSendService mqMsgSendService;

    @SysLogAop("MQ消息重试管理首页")
    @GetMapping(value = "/toPage")
    @RequiresPermissions("mq:msg:send:toPage")
    public String toPage(Model model) {
        List<EnumDataStructureResp> typeList = new ArrayList<>();
        Arrays.stream(EnumMqMsgSendType.values()).forEach(o -> {
            EnumDataStructureResp statusResp = new EnumDataStructureResp();
            statusResp.setCode(o.getCode());
            statusResp.setDescription(o.getDescription());
            typeList.add(statusResp);
        });
        model.addAttribute("typeMap", JSONObject.toJSONString(typeList));
        return "wallet/mq-msg-send-reissue";
    }

    @SysLogAop("MQ消息重试列表数据")
    @PostMapping(value = "/list/page")
    @ResponseBody
    @RequiresPermissions("mq:msg:send:toPage")
    public BaseResult listPage(MqMsgSendListPageReq req, HttpSession session) {
        return mqMsgSendService.listPage(req, session);
    }

    @SysLogAop(value = "取消MQ消息重试处理", monitor = true)
    @PostMapping(value = "/cancel")
    @ResponseBody
    @RequiresPermissions("mq:msg:send:toPage")
    public BaseResult cancel(MqMsgSendCancelReq req, HttpSession session) {
        return mqMsgSendService.cancel(req, session);
    }

    @SysLogAop(value = "MQ消息手动重试", monitor = true)
    @PostMapping(value = "/retry")
    @ResponseBody
    @RequiresPermissions("mq:msg:send:toPage")
    public BaseResult retry(MqMsgSendRetryReq req, HttpSession session) {
        return mqMsgSendService.retry(req, session);
    }
}



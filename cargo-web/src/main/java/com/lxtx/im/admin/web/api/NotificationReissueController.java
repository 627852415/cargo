package com.lxtx.im.admin.web.api;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.NotificationReissueService;
import com.lxtx.im.admin.service.enums.EnumNotificationReissueType;
import com.lxtx.im.admin.service.request.NotificationReissueHandleReq;
import com.lxtx.im.admin.service.request.NotificationReissuePageReq;
import com.lxtx.im.admin.service.response.NotificationReissueTypeResp;
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
 * 通知重发
 *
 * @author CaiRH
 * @since 2019-06-12
 */
@Controller
@RequestMapping("/notification/reissue")
public class NotificationReissueController {

    @Autowired
    private NotificationReissueService notificationReissueService;

    @SysLogAop("通知重发首页")
    @GetMapping(value = "/toPage")
    @RequiresPermissions("notification:reissue:toPage")
    public String toPage(Model model) {
        List<NotificationReissueTypeResp> typeList = new ArrayList<>();
        Arrays.stream(EnumNotificationReissueType.values()).forEach(o -> {
            NotificationReissueTypeResp statusResp = new NotificationReissueTypeResp();
            statusResp.setCode(o.getCode());
            statusResp.setDescription(o.getDescription());
            typeList.add(statusResp);
        });
        model.addAttribute("typeMap", JSONObject.toJSONString(typeList));
        return "wallet/notification-reissue";
    }

    @SysLogAop("通知重发列表数据")
    @PostMapping(value = "/list/page")
    @ResponseBody
    @RequiresPermissions("notification:reissue:toPage")
    public BaseResult listPage(NotificationReissuePageReq req, HttpSession session) {
        return notificationReissueService.listPage(req, session);
    }

    @SysLogAop(value = "通知重发处理", monitor = true)
    @PostMapping(value = "/handle")
    @ResponseBody
    @RequiresPermissions("notification:reissue:toPage")
    public BaseResult handle(NotificationReissueHandleReq req, HttpSession session) {
        return notificationReissueService.handle(req, session);
    }
}



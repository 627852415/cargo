package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.GroupMemberService;
import com.lxtx.im.admin.service.request.GroupListPageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 群成员控制器
 *
 * @Author liyunhua
 * @Date 2018-09-11 0011
 */

@Controller
@RequestMapping("/groupMember")
public class GroupMemberController {

    @Autowired
    private GroupMemberService groupMemberService;



    @SysLogAop("群成员首页")
    @GetMapping(value = "/index")
    public String detail(Model model, GroupListPageReq req) {
        model.addAttribute("groupId", req.getGroupId());
        return "group/group-member-index";
    }


    @PostMapping(value = "/init/gmid")
    @ResponseBody
    public BaseResult initGmId() {
        return groupMemberService.initGmId();
    }

    /**
     * 搜索群成员列表
     *
     * @param groupListPageReq, session
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-08-30 0030
     */
    @SysLogAop("搜索群成员列表")
    @PostMapping(value = "/listPage")
    @ResponseBody
    public BaseResult listPage(GroupListPageReq groupListPageReq, HttpSession session) {
        return groupMemberService.listPage(groupListPageReq);
    }

    /**
     * 同步所有IM用户群消息免打扰同步云信
     *
     * @param
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/3/7
     */
    @SysLogAop(value = "群消息免打扰同步云信", monitor = true)
    @PostMapping(value = "/yxGroupMemberBotherSyn")
    @ResponseBody
    public BaseResult yxGroupMemberBotherSyn() {
        return groupMemberService.yxGroupMemberBotherSyn();
    }

}

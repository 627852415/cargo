package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.MemberService;
import com.lxtx.im.admin.service.request.MemberReq;
import com.lxtx.im.admin.service.request.TurnUserStatusReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 会员管理控制器
 *
 * @Author liyunhua
 * @Date 2018-08-30 0030
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;


    /**
     * 会员管理首页
     *
     * @param
     * @return java.lang.String
     * @author liyunhua
     * @date 2018-08-30 0030
     */
    @SysLogAop("钱包会员管理首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("member:index")
    public String index() {
        return "admin/member-index";
    }


    /**
     * 搜索会员列表
     *
     * @param memberReq, session
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-08-30 0030
     */
    @SysLogAop("钱包会员管理搜索会员列表")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("member:index")
    public BaseResult list(MemberReq memberReq, HttpSession session) {
        return memberService.list(memberReq, session);
    }

    /**
     * 更改用户状态（禁用/开启）
     *
     * @param turnUserStatusReq
     * @return
     */
    @SysLogAop(value = "钱包会员管理更改用户状态", monitor = true)
    @PostMapping("/status/turn")
    @ResponseBody
    @RequiresPermissions("status:turn:member")
    public BaseResult turnStatus(@Valid @RequestBody TurnUserStatusReq turnUserStatusReq) {
        return memberService.turnUserStatus(turnUserStatusReq);
    }

//    /**
//     * 同步IM用户-钱包用户
//     *
//     * @return
//     */
//    @SysLogAop("同步IM用户-钱包用户")
//    @PostMapping("/synchronize/im")
//    @ResponseBody
//    public BaseResult synchronizeUser() {
//        return memberService.synchronizeUser();
//    }

}

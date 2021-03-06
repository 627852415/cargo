package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.SysMenu;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.SysRoleService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.web.request.LoginReq;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private SysRoleService sysRoleService;

    @ResponseBody
    @RequestMapping("/login")
    public BaseResult login(@RequestBody  LoginReq req , HttpSession session) {
        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(req.getUsername(), req.getPassword());
            subject.login(token);
        }catch (UnknownAccountException e) {
            throw LxtxBizException.newException(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            throw LxtxBizException.newException(e.getMessage());
        }catch (LockedAccountException e) {
            throw LxtxBizException.newException(e.getMessage());
        }catch (AuthenticationException e) {
            throw LxtxBizException.newException("账号密码错误");
        }
        SysUser sysUser = ShiroUtils.getUserEntity();
        sysUser.setPassword(null);
        session.setAttribute("userInfo",sysUser);
        //加载用户菜单
        List<SysMenu> menuList = sysRoleService.selectRolePermsByUser(sysUser);
        session.setAttribute("menuList",menuList);
        return BaseResult.success(sysUser);
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

}

package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.IPAddressUtil;
import com.lxtx.im.admin.service.LoginService;
import com.lxtx.im.admin.service.SysUserService;
import com.lxtx.im.admin.service.WebUsbTokenService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/manage")
public class LoginController {

    @Value("${app.ca}")
    private String ca;

    @Autowired
    private LoginService loginService;
    @Autowired
    private WebUsbTokenService webUsbTokenService;
    @Autowired
    private SysUserService sysUserService;

    @SysLogAop("获取usbToken开关")
    @GetMapping(value = "/usb/token/switch")
    @ResponseBody
    public BaseResult usbTokenSwitch() {
        return webUsbTokenService.isOpenUsbToken();
    }

    @SysLogAop("usbToken验证")
    @PostMapping(value = "/usb/token/verify")
    @ResponseBody
    public BaseResult verifyToken(@RequestHeader String token, @Validated @RequestBody UsbTokenReq usbTokenReq, HttpServletRequest request)  {
        usbTokenReq.setCa(ca);
        usbTokenReq.setToken(token);
        usbTokenReq.setIp(IPAddressUtil.getIpAddress(request));
        return webUsbTokenService.verifyToken(usbTokenReq);
    }

    @GetMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    @SysLogAop("跳转首页")
    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @SysLogAop("前往修改密码")
    @GetMapping(value = "/to/update/pass")
    public String toUpdatePassword() {
        return "updatePassword";
    }

    @SysLogAop(value = "修改密码", monitor = true, param = false)
    @PostMapping(value = "/update/pwd")
    @ResponseBody
    public BaseResult updatePwd(@Validated @RequestBody  SysUserModifyPwdReq sysUserModifyPwdReq) {
       return sysUserService.updatePwd(sysUserModifyPwdReq);
    }

    /**
     * 登录
     *
     * @return
     * @author tangdy
     * @return
     */
    @SysLogAop(value = "登录", param = false)
    @PostMapping(value = "/login")
    @ResponseBody
    public BaseResult login(@Validated @RequestBody LoginReq req, HttpSession session) {
        return loginService.login(req,session);
    }

    /**
     * 系统退出
     *
     * @return
     */
    @SysLogAop(value = "用户登出")
    @GetMapping(value = "/logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/manage/toLogin";
    }

    /**
     * 跳转谷歌认证设置页面
     *
     * @return
     * @author tangdy
     * @return
     */
    @GetMapping(value = "/google/Authenticator/index")
    public String authenticatorIndex() {
        return "google-authenticator-index";
    }

    /**
     * 简要说明：获取QR码
     * @author tangdayong
     * 创建时间：2018年7月10日 下午6:01:53
     */
    @PostMapping(value = "/getQrCode")
    @ResponseBody
    public BaseResult getQrCode(){
        return loginService.getQrCode();
    }


    /**
     * 简要说明：谷歌验证码设置并加载菜单数据
     * @author tangdayong
     * 创建时间：2018年7月10日 下午6:01:53
     */
    @PostMapping(value = "/googleAuthenticator")
    @ResponseBody
    public BaseResult googleAuthenticator(@Valid @RequestBody GoogleAuthenticatorReq req, HttpSession session){
        return loginService.googleAuthenticator(req, session);
    }

    /**
     * 简要说明：谷歌验证登录
     * @author tangdayong
     * 创建时间：2018年7月10日 下午6:01:53
     */
    @PostMapping(value = "/googleLogin")
    @ResponseBody
    public BaseResult googleLogin(@Valid @RequestBody GoogleLoginReq req, HttpSession session){
        return loginService.googleLogin(req, session);
    }

    /**
     * 跳转至未授权页面
     * @return
     */
    @RequestMapping(value = "/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}

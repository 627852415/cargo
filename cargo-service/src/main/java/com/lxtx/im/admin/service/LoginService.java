package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.GoogleAuthenticatorReq;
import com.lxtx.im.admin.service.request.GoogleLoginReq;
import com.lxtx.im.admin.service.request.LoginReq;
import com.lxtx.im.admin.service.request.SysLoginReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface LoginService {
    /**
     * 登录
     *
     * @param req
     * @return
     */
    BaseResult login(LoginReq req, HttpSession session);

    /**
     * 获取QRcode
     * @return
     */
    BaseResult getQrCode();

    /**
     * 谷歌验证码设置并加载菜单数据
     * @return
     */
    BaseResult googleAuthenticator(GoogleAuthenticatorReq req, HttpSession session);

    /**
     * 谷歌认证
     * @param req
     * @param session
     * @return
     */
    BaseResult googleLogin(GoogleLoginReq req, HttpSession session);
}

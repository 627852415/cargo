package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.encrypt.Md5;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.framework.common.utils.google.GoogleAuthenticatorUtil;
import com.lxtx.framework.common.utils.qrcode.QRCodeUtil;
import com.lxtx.framework.common.utils.qrcode.QrCodeModel;
import com.lxtx.im.admin.dao.SysUserDao;
import com.lxtx.im.admin.dao.model.SysMenu;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.Constants.Constants;
import com.lxtx.im.admin.service.Constants.PropertiesContants;
import com.lxtx.im.admin.service.LoginService;
import com.lxtx.im.admin.service.SysRoleService;
import com.lxtx.im.admin.service.enums.EnumUsbTokenVerifyStatus;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.GoogleAuthenticatorReq;
import com.lxtx.im.admin.service.request.GoogleLoginReq;
import com.lxtx.im.admin.service.request.LoginReq;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 登录
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult login(LoginReq req, HttpSession session) {

        if (req.getUsbTokenStatus() != null) {
            throw LxtxBizException.newException(String.valueOf(req.getUsbTokenStatus()), EnumUsbTokenVerifyStatus.find(req.getUsbTokenStatus()).getDescription());
        }

        try{
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
//            String password = new Sha256Hash(req.getPassword()).toHex();
//            String password = Md5.encode(req.getPassword());
            UsernamePasswordToken token = new UsernamePasswordToken(req.getUsername(), req.getPassword());
            subject.login(token);
        }catch (UnknownAccountException e) {
            throw LxtxBizException.newException(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            throw LxtxBizException.newException(e.getMessage());
        }catch (LockedAccountException e) {
            throw LxtxBizException.newException(e.getMessage());
        }catch (AuthenticationException e) {
            throw LxtxBizException.newExceptionWithLocale("errors.username.or.password");
        }
        SysUser sysUser = ShiroUtils.getUserEntity();
        session.setAttribute("userInfo",sysUser);
//        String unauthorizeUserName = PropertiesUtil.getString(PropertiesContants.GOOGLE_UNAUTHORIZE_USERNAME);
//        if (StringUtils.isNotBlank(unauthorizeUserName) && unauthorizeUserName.equals(sysUser.getUsername())){
//            //加载用户菜单
//            List<SysMenu> menuList = sysRoleService.selectRolePermsByUser(sysUser);
//            session.setAttribute("menuList",menuList);
//            return BaseResult.success();
//        }else{
//        }

            return BaseResult.success(sysUser);
    }

    @Override
    public BaseResult getQrCode() {
        String googlAuthorizeName = PropertiesUtil.getString(PropertiesContants.GOOGLE_AUTHORIZE_NAME);
        Map<String, Object> map = new HashMap<>();
        String secret = GoogleAuthenticatorUtil.generateSecretKey();
        StringBuilder builder = new StringBuilder();
        builder.append(googlAuthorizeName);
        builder.append(":");
        builder.append(ShiroUtils.getUserEntity().getUsername());
        String qrcode = GoogleAuthenticatorUtil.getQRBarcode(builder.toString(), secret);
        QrCodeModel info = new QrCodeModel();
        info.setContents(qrcode);
        info.setWidth(180);
        info.setHeight(180);
        map.put("qrcodeData", "data:image/png;base64," + QRCodeUtil.getCodeImageBinary(info));
        map.put("qrcodeTitle", qrcode);
        map.put("secret", secret);
        return BaseResult.success(map);
    }

    /**
     * 谷歌验证码设置并加载菜单数据
     * @return
     */
    @Override
    public BaseResult googleAuthenticator(GoogleAuthenticatorReq req, HttpSession session) {
        String secret = req.getSecret();
        boolean r = googleCheck(secret, req.getQrCode());
        if (r) {
            SysUser user = ShiroUtils.getUserEntity();
            //更新用户信息
            if(user == null){
                throw LxtxBizException.newException("用户信息有误，请刷新页面重试！");
            }
            user.setGoogleCipher(secret);
            user.setUpdateBy(user.getUserId());
            user.setFirstLogin(Constants.FIRST_LOGIN_FALSE);
            sysUserDao.updateById(user);
            //加载用户菜单
            List<SysMenu> menuList = sysRoleService.selectRolePermsByUser(user);
            session.setAttribute("menuList",menuList);
            return BaseResult.success("谷歌认证设置成功");
        }else{
            throw LxtxBizException.newException("谷歌认证设置失败");
        }
    }

    @Override
    public BaseResult googleLogin(GoogleLoginReq req, HttpSession session) {
        boolean r = googleCheck(ShiroUtils.getUserEntity().getGoogleCipher(), req.getQrCode());
        if (r) {
            SysUser sysUser = ShiroUtils.getUserEntity();
            //更新用户信息
            if(sysUser == null){
                throw LxtxBizException.newException("用户信息有误，请刷新页面重试！");
            }
            //加载用户菜单
            List<SysMenu> menuList = sysRoleService.selectRolePermsByUser(sysUser);
            session.setAttribute("menuList",menuList);
            return BaseResult.success("谷歌认证成功");
        }else{
            return BaseResult.error(null,"谷歌认证失败");
        }
    }

    private boolean googleCheck(String secret, long qrCode){
        long t = System.currentTimeMillis();
        GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
        ga.setWindowSize(5);
        return ga.checkCode(secret, qrCode, t);
    }

}

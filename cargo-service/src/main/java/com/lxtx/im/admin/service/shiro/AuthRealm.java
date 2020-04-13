package com.lxtx.im.admin.service.shiro;

import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.dao.SysMenuDao;
import com.lxtx.im.admin.dao.SysUserDao;
import com.lxtx.im.admin.dao.model.SysMenu;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.Constants.PropertiesContants;
import com.lxtx.im.admin.service.SysRoleService;
import com.lxtx.im.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Czh Date: 2018/9/27 下午2:16
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    @Lazy
    private SysRoleService sysRoleService;
    @Autowired
    private UserService userService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        String userId = user.getUserId();

        List<SysMenu> menuList;
        List<String> superUsernameList = PropertiesUtil.getList(PropertiesContants.SUPER_USERNAME, ",");
        // 系统管理员，拥有最高权限
        if (!CollectionUtils.isEmpty(superUsernameList) && superUsernameList.contains(user.getUsername())) {
            menuList = sysMenuDao.selectList(new SysMenu());

        } else {

            menuList = sysRoleService.selectRolePermsByUser(user);
        }
        List<String> permsList = new ArrayList<>(menuList.size());
        for (SysMenu menu : menuList) {
            permsList.add(menu.getPerms());
        }

        // 用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        Subject subject = ShiroUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("isShowAccount",userService.isShowAccount());
        session.setAttribute("permsSet",permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        SysUser condition = new SysUser();
        condition.setUsername(username);
        // 查询用户信息
        SysUser user = sysUserDao.selectOne(condition);

        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        // 账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }


}
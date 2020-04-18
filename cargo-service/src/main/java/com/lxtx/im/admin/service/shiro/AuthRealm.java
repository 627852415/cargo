package com.lxtx.im.admin.service.shiro;

import com.lxtx.im.admin.dao.SysMenuDao;
import com.lxtx.im.admin.dao.SysUserDao;
import com.lxtx.im.admin.dao.model.SysMenu;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();

        List<SysMenu> menuList;
        List<String> superUsernameList = Arrays.asList("admin","smanager");
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
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }


}
package com.lxtx.im.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.encrypt.Md5;
import com.lxtx.im.admin.dao.SysRoleDao;
import com.lxtx.im.admin.dao.SysUserDao;
import com.lxtx.im.admin.dao.SysUserRoleDao;
import com.lxtx.im.admin.dao.model.SysRole;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.dao.model.SysUserRole;
import com.lxtx.im.admin.service.SysUserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.SysUserListPageReq;
import com.lxtx.im.admin.service.request.SysUserModifyPwdReq;
import com.lxtx.im.admin.service.request.SysUserModifyReq;
import com.lxtx.im.admin.service.request.SysUserPassModifyReq;
import com.lxtx.im.admin.service.response.SysUserListPageResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleDao sysRoleDao;


    private static final String DEFAULT_PWD = "123456";

    @Override
    public BaseResult listPage(SysUserListPageReq req) {
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(req.getUsername())) {
            ew.like("username", req.getUsername());
        }
        if (StringUtils.isNotEmpty(req.getMobile())) {
            ew.like("mobile", req.getMobile());
        }

        SysUserListPageResp userListPageResp = new SysUserListPageResp();
        ew.orderBy("create_time", false);
        Page<SysUser> page = sysUserDao.selectPage(req.getPage(), ew);
        BeanUtils.copyProperties(page, userListPageResp);

        //查询用户已拥有的角色
        List<SysUser> records = userListPageResp.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            List<String> roleIdList;
            for (SysUser record : records) {
                roleIdList = new ArrayList<>();
                //查询用户所属角色
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(record.getUserId());
                List<SysUserRole> sysUserRoles = sysUserRoleDao.selectList(sysUserRole);
                if (!CollectionUtils.isEmpty(sysUserRoles)) {
                    StringBuilder sb = new StringBuilder();
                    for (SysUserRole userRole : sysUserRoles) {
                        roleIdList.add(userRole.getRoleId());
                        SysRole sysRole = sysRoleDao.selectById(userRole.getRoleId());
                        if (sysRole != null) {
                            sb.append(sysRole.getRoleName() + ",");
                        }
                    }
                    if (sb.length() > 0) {
                        record.setRoleName(sb.substring(0, sb.length() - 1));
                    }
                    //用户已拥有的角色
                    record.setRoleIdList(roleIdList);
                }
            }
        }
        return BaseResult.success(userListPageResp);
    }

    /**
     * 检查用户名是否已存在
     *
     * @param username
     * @return java.lang.Boolean
     * @author liyunhua
     * @date 2018-10-08 0008
     */
    private Boolean checkUserNameExist(String username, String userId) {
        SysUser userDB = null;
        if (StringUtils.isBlank(userId)) {
            SysUser user = new SysUser();
            user.setUsername(username);
            userDB = sysUserDao.selectOne(user);
        } else {
            EntityWrapper<SysUser> ew = new EntityWrapper<>();
            ew.eq("username", username);
            ew.notIn("user_id", userId);
            userDB = sysUserDao.selectOne(ew);
        }
        if (userDB != null) {
            return true;
        }
        return false;
    }


    public  String getStringNoBlank(String str) {
        if (str != null && !"".equals(str)) {
            str.replaceAll("\n", "");
            Pattern p = Pattern.compile("(^\\s*)|(\\s*$)");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            return strNoBlank;
        } else {
            return "";
        }
    }

    @Override
    public BaseResult modify(SysUserModifyReq req) {

        req.setUsbTokenNo(getStringNoBlank(req.getUsbTokenNo()));
        req.setIp(getStringNoBlank(req.getIp()));
        if(StringUtils.isEmpty(req.getUsername())){
            throw LxtxBizException.newException("操作失败,请输入用户名！");
        }
        String userId = req.getUserId();
        String username = req.getUsername();
        SysUser updateUser = new SysUser();
        BeanUtils.copyProperties(req, updateUser);
        if(StringUtils.isNotEmpty(req.getIsUpdate())&&req.getIsUpdate().equals("true")) {
            updateUser.setPassword(null);
            req.setPassword(null);
        }else{
            if(StringUtils.isEmpty(req.getPassword())){
                throw LxtxBizException.newException("操作失败,请输入密码！");
            }
            if(StringUtils.isEmpty(req.getPassword2())){
                throw LxtxBizException.newException("操作失败,请输入确认密码！");
            }
            if(!req.getPassword2().equals(req.getPassword())){
                throw LxtxBizException.newException("操作失败,两次密码输入不一致！");
            }
        }
        Boolean b;
        String password = req.getPassword();
        if (StringUtils.isBlank(userId)) {
            if (checkUserNameExist(username, null)) {
                throw LxtxBizException.newException("操作失败,用户名已存在！");
            }
            if (StringUtils.isBlank(password)) {
                password = Md5.encode(DEFAULT_PWD);
            }
            updateUser.setPassword(password);
            updateUser.setStatus(1);
            b = sysUserDao.insert(updateUser);
        } else {
            if (checkUserNameExist(username, userId)) {
                throw LxtxBizException.newException("操作失败,用户名已存在！");
            }
            SysUser user = sysUserDao.selectById(userId);
            if (!user.getPassword().equals(password)) {
                updateUser.setPassword(password);
            }
            updateUser.setUserId(req.getUserId());
            b = sysUserDao.insertOrUpdate(updateUser);
        }

        //修改用户角色
        List<String> roleIdList = req.getRoleIdList();
        if (CollectionUtils.isEmpty(roleIdList)) {
            throw LxtxBizException.newException("操作失败,请设置用户角色！");
        }
        //先删除该用户所有角色，再重新设置
        SysUserRole sysUserRoleDelete = new SysUserRole();
        sysUserRoleDelete.setUserId(updateUser.getUserId());
        sysUserRoleDao.delete(sysUserRoleDelete);
        SysUserRole sysUserRole;
        for (String roleId : roleIdList) {
            if (StringUtils.isNotBlank(roleId)) {
                sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(updateUser.getUserId());
                sysUserRoleDao.insertOrUpdate(sysUserRole);
            }
        }
        if (!b) {
            throw LxtxBizException.newException("操作失败");
        }
        return BaseResult.success("操作成功");
    }

    public BaseResult modify2(SysUserPassModifyReq req) {
        String userId = req.getUserId();
        SysUser updateUser = new SysUser();
        BeanUtils.copyProperties(req, updateUser);
        if(StringUtils.isEmpty(req.getPassword())){
            throw LxtxBizException.newException("操作失败,请输入密码！");
        }
        if(StringUtils.isEmpty(req.getPassword2())){
            throw LxtxBizException.newException("操作失败,请输入确认密码！");
        }
        if(!req.getPassword2().equals(req.getPassword())){
            throw LxtxBizException.newException("操作失败,两次密码输入不一致！");
        }
        String password = req.getPassword();
        SysUser user = sysUserDao.selectById(userId);
        if(user==null){
            throw LxtxBizException.newException("用户不存在！");
        }
        updateUser.setPassword(password);
        updateUser.setUserId(req.getUserId());
        sysUserDao.updateById(updateUser);
        return BaseResult.success("操作成功");
    }

    @Override
    public BaseResult delete(SysUserModifyReq req) {
        SysUser updateUser = new SysUser();
        updateUser.setUserId(req.getUserId());
        updateUser.setDelFlag(true);
        boolean b = sysUserDao.insertOrUpdate(updateUser);
        if (!b) {
            throw LxtxBizException.newException("操作失败");
        }
        return BaseResult.success("操作成功");
    }

    @Override
    public BaseResult resetPwd(SysUserModifyReq req) {
        SysUser updateUser = new SysUser();
        updateUser.setUserId(req.getUserId());
        String password = Md5.encode(DEFAULT_PWD);
        updateUser.setPassword(password);
        boolean b = sysUserDao.insertOrUpdate(updateUser);
        if (!b) {
            throw LxtxBizException.newException("操作失败");
        }
        return BaseResult.success("操作成功");
    }

    public BaseResult updatePwd(SysUserModifyPwdReq sysUserModifyPwdReq) {
        SysUser sysUser = this.selectById(ShiroUtils.getUserId());
        if(!sysUser.getPassword().equals(sysUserModifyPwdReq.getOldPwd())){
            throw LxtxBizException.newException("请输入正确的旧密码!");
        }
        if(!sysUserModifyPwdReq.getComfirPassword().equals(sysUserModifyPwdReq.getPassword())){
            throw LxtxBizException.newException("两次密码输入不一致!");
        }
        SysUser updateUser = new SysUser();
        updateUser.setUserId(ShiroUtils.getUserId());
        updateUser.setPassword(sysUserModifyPwdReq.getPassword());

        boolean b = sysUserDao.insertOrUpdate(updateUser);
        if (!b) {
            throw LxtxBizException.newException("操作失败");
        }
        return BaseResult.success("操作成功");
    }

    @Override
    public BaseResult statusChange(SysUserModifyReq req) {
        SysUser updateUser = new SysUser();
        updateUser.setUserId(req.getUserId());
        updateUser.setStatus(req.getStatus());
        boolean b = sysUserDao.insertOrUpdate(updateUser);
        if (!b) {
            throw LxtxBizException.newException("操作失败");
        }
        return BaseResult.success("操作成功");
    }

    @Override
    public SysUser selectById(String id) {
        return sysUserDao.selectById(id);
    }


    public SysUser selectOne(SysUser sysUser) {
        return sysUserDao.selectOne(sysUser);
    }
}

package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.request.SysUserListPageReq;
import com.lxtx.im.admin.service.request.SysUserModifyPwdReq;
import com.lxtx.im.admin.service.request.SysUserModifyReq;
import com.lxtx.im.admin.service.request.SysUserPassModifyReq;

public interface SysUserService {

    /**
     * 根据用户主键查询
     * @param id
     * @return
     */
    SysUser selectById(String id);

    SysUser selectOne(SysUser sysUser);

    /**
     * 获取系统用户列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(SysUserListPageReq req);


    /**
     * 新增或修改系统用户信息
     *
     * @param req
     * @return
     */
    BaseResult modify(SysUserModifyReq req);

    BaseResult modify2(SysUserPassModifyReq req);

    /**
     * 删除系统用户
     *
     * @param req
     * @return
     */
    BaseResult delete(SysUserModifyReq req);

    /**
     * 重置用户密码为123456
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    BaseResult resetPwd(SysUserModifyReq req);


     /**   修改用户密码
     　　* @author Lin hj
     　　* @date 2019/7/10 10:45
     */
    BaseResult updatePwd(SysUserModifyPwdReq sysUserModifyPwdReq);

    /**
     * 启用禁用用户
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    BaseResult statusChange(SysUserModifyReq req);
}

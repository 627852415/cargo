package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @description:  角色列表返回
* @author:   CXM
* @create:   2018-09-27 15:15
*/
@Getter
@Setter
public class SysRoleResp {

    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 状态【0:启用，1：禁用】
     */
    private String status;
    /**
     * 创建时间
     */
    public Date createTime;
}

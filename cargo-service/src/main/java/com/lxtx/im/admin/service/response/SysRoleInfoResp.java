package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @description:  角色信息返回
* @author:   CXM
* @create:   2018-09-27 15:15
*/
@Getter
@Setter
public class SysRoleInfoResp {

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
     * 状态【0:启用，1：禁用】
     */
    private String status;
    /**
     * 权限菜单
     */
    private List<String> menuIdList;
}

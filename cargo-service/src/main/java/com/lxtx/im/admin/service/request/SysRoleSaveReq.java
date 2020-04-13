package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  保存角色入参
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class SysRoleSaveReq {
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;
    /**
     * 角色状态
     */
    @NotBlank(message = "角色状态不能为空")
    private String status;
    /**
     * 权限菜单ID
     */
    private String[] menuIdList;
}

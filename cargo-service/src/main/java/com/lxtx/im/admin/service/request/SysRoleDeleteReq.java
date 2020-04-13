package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  删除角色参数类
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class SysRoleDeleteReq {
    /**
     * 角色ID
     */
    @NotBlank(message = "角色ID不能为空")
    private String roleId;
}

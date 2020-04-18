package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 获取角色列表入参
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class SysRoleReq extends BasePageReq {
    /**
     * 角色名称
     */
    private String roleName;
}
